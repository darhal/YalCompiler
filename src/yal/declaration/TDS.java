package yal.declaration;

import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbol;

import java.util.HashMap;

public class TDS
{
    private static TDS instance = new TDS();

    private TDS()
    {
        symbolMap = new HashMap<>();
    }

    public HashMap<Entry, Symbol> symbolMap;

    public static TDS Instance()
    {
        return instance;
    }

    public void AddEntry(Entry e, Symbol s)
    {
        if (symbolMap.get(e) != null){
            System.out.println("Erreur: Double declaration.");
        }

        s.incrementOffset(getVariableZoneSize());
        symbolMap.put(e, s);
    }

    public Symbol Identify(Entry e)
    {
        return symbolMap.get(e);
    }

    public int getVariableZoneSize()
    {
        return symbolMap.size();
    }
}
