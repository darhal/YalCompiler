package yal.declaration;

import yal.arbre.expressions.Variable;
import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.entries.VariableEntry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.IdentifiantNonDeclarerException;

import java.util.HashMap;

/**
 * Class TableLocal
 */
public class TableLocal
{
    protected HashMap<Entry, Symbole> symbolMap;
    protected FonctionEntry fnEntry;
    protected int noBlocGlobal;
    protected int noBloc;

    public TableLocal(int bloc, int globalBloc)
    {
        symbolMap = new HashMap<>();
        noBloc = bloc;
        noBlocGlobal = globalBloc;
        fnEntry = new FonctionEntry();
    }

    /**
     * AddEntry
     * @param e
     * @param s
     * @param line
     * @throws DoubleDeclarationException
     */
    public void AddEntry(Entry e, Symbole s, int line) throws DoubleDeclarationException
    {
        if (symbolMap.get(e) != null){
            throw new DoubleDeclarationException(line);
        }

        if (s.isParam()){
            s.setOffset(getArgsZoneSize());
        }else{
            s.setOffset(getLocalVarSize());
        }

        s.setNoBloc(noBloc);
        symbolMap.put(e, s);
    }

    /**
     * Identify
     * @param e
     * @return
     * @throws IdentifiantNonDeclarerException
     */
    public Symbole Identify(Entry e) throws IdentifiantNonDeclarerException
    {
        Symbole s = symbolMap.get(e);
        int parentBloc = noBlocGlobal;

        while (s == null && parentBloc != -1){
            TableLocal l = TDS.Instance().getBloc(parentBloc);
            s = l.Identify(e);
            parentBloc = l.getNoBlocGlobal();
        }

        if (s == null){
            throw new IdentifiantNonDeclarerException(e.getLine(), e.getIdentifier());
        }

        return s;
    }

    public int getNoBloc()
    {
        return noBloc;
    }

    public int getNoBlocGlobal()
    {
        return noBlocGlobal;
    }

    public FonctionEntry getFnEntry() {
        return fnEntry;
    }

    public int getVariableZoneSize()
    {
        return symbolMap.size();
    }

    public int getArgsZoneSize()
    {
        int i = 0;

        for (Symbole s : TDS.Instance().getSymbolMap().values()) {
            if (s.isParam()) {
                i++;
            }
        }

        return i;
    }

    public int getLocalVarSize()
    {
        int i = 0;

        for (Symbole s : TDS.Instance().getSymbolMap().values()) {
            if (s.isVariable() || s.isArray()) {
                i++;
            }
        }

        return i;
    }

    public HashMap<Entry, Symbole> getSymbolMap() {
        return symbolMap;
    }
}
