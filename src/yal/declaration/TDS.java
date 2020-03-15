package yal.declaration;

import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.IdentifiantNonDeclarerException;

import java.util.ArrayList;
import java.util.HashMap;

public class TDS
{
    private static TDS instance = new TDS();

    public HashMap<FonctionEntry, FonctionSymbole> fnSymbolMap;
    public ArrayList<TableLocal> blocs;
    public int currentBloc;

    private TDS()
    {
        fnSymbolMap = new HashMap<>();
        blocs = new ArrayList<>();
        blocs.add(new TableLocal(0, -1));
        currentBloc = 0;
    }

    public static TDS Instance()
    {
        return instance;
    }

    public void AddEntry(Entry e, Symbole s, int line)
    {
        this.getBloc(currentBloc).AddEntry(e, s, line);
    }

    public Symbole Identify(Entry e) throws IdentifiantNonDeclarerException
    {
        return this.getBloc(currentBloc).Identify(e);
    }

    public void addFunctionEntry(FonctionEntry e, FonctionSymbole s, int line)
    {
        if (fnSymbolMap.get(e) != null){
            throw new DoubleDeclarationException(line);
        }

        s.incrementOffset(getVariableZoneSize());
        fnSymbolMap.put(e, s);
    }

    public Symbole IdentifyFunction(FonctionEntry e) throws IdentifiantNonDeclarerException
    {
        FonctionSymbole s = fnSymbolMap.get(e);
        if (s == null){
            throw new IdentifiantNonDeclarerException(e.getLine());
        }

        return s;
    }

    public void EnterBloc()
    {
        currentBloc = blocs.size();
        blocs.add(new TableLocal(currentBloc, 0));
    }

    public void EnterBloc(int noBloc)
    {
        if (noBloc < blocs.size()){
            currentBloc = noBloc;
        }
    }

    public void LeaveBloc()
    {
        currentBloc = 0;
    }

    public TableLocal getBloc(int index)
    {
        return blocs.get(index);
    }

    public int getCurrentBloc()
    {
        return currentBloc;
    }

    public ArrayList<TableLocal> getBlocs() {
        return blocs;
    }

    public int getVariableZoneSize()
    {
        return this.getBloc(currentBloc).getVariableZoneSize();
    }
}
