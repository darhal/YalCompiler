package yal.declaration;

import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.IdentifiantNonDeclarerException;

import java.util.HashMap;

public class TDS
{
    private static TDS instance = new TDS();

    private TDS()
    {
        symbolMap = new HashMap<>();
    }

    public HashMap<Entry, Symbole> symbolMap;

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

    public int getVariableZoneSize()
    {
        int sz = 0;

        for (Entry s : symbolMap.keySet()){
            if (s.getDecltype() == Decltype.VARIABLE){
                sz++;
            }
        }

        return sz;
    }
}
