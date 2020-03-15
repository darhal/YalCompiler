package yal.declaration;

import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.IdentifiantNonDeclarerException;

import java.util.HashMap;

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

    public void AddEntry(Entry e, Symbole s, int line) throws DoubleDeclarationException
    {
        if (symbolMap.get(e) != null){
            throw new DoubleDeclarationException(line);
        }

        s.incrementOffset(getVariableZoneSize());
        s.setNoBloc(noBloc);
        symbolMap.put(e, s);
    }

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
            throw new IdentifiantNonDeclarerException(e.getLine(), ((FonctionEntry)e).getFunctionName());
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
        int nbParam = fnEntry.getNbParam();
        return nbParam;
    }

    public int getLocalVarSize()
    {
        int nbParam = fnEntry.getNbParam();
        return symbolMap.size() - nbParam;
    }
}
