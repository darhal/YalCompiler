package yal.declaration;

import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.IdentifiantNonDeclarerException;

import java.util.ArrayList;
import java.util.HashMap;

public class TDS
{
    private static TDS instance = new TDS();

    public HashMap<Entry, Symbole> symbolMap;
    public ArrayList<FonctionEntry> blocs;
    public int currentBloc;

    private TDS()
    {
        symbolMap = new HashMap<>();
        blocs = new ArrayList<>();
        currentBloc = -1;
    }

    public static TDS Instance()
    {
        return instance;
    }

    public void AddEntry(Entry e, Symbole s, int line) throws DoubleDeclarationException
    {
        if (symbolMap.get(e) != null){
            throw new DoubleDeclarationException(line);
        }

        s.incrementOffset(getVariableZoneSize());
        symbolMap.put(e, s);
    }

    public Symbole Identify(Entry e) throws IdentifiantNonDeclarerException
    {
        Symbole s = symbolMap.get(e);
        if (s == null){
            throw new IdentifiantNonDeclarerException(e.getLine());
        }

        return s;
    }

    public void EnterBloc()
    {
        currentBloc = blocs.size();
        blocs.add(new FonctionEntry());
    }

    public void LeaveBloc()
    {
        FonctionEntry entree = TDS.Instance().getBloc(TDS.Instance().getCurrentBloc());
        if (entree.getNbReturn() == 0){
            throw new AnalyseSemantiqueException(entree.getLine(),
                    "Functions must have at least one return statement, the function '"+entree.getIdentifier()+"' doesn't have any return statement"
            );
        }
        currentBloc = -1;
    }

    public FonctionEntry getBloc(int index)
    {
        return blocs.get(index);
    }

    public int getCurrentBloc()
    {
        return currentBloc;
    }

    public int getVariableZoneSize()
    {
        /*int sz = 0;

        for (Entry s : symbolMap.keySet()){
            if (s.getDecltype() == Decltype.VARIABLE){
                sz++;
            }
        }*/

        return symbolMap.size();
    }
}
