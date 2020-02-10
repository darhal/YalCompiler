package yal.declaration;

import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbol;
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

    public HashMap<Entry, Symbol> symbolMap;

    public static TDS Instance()
    {
        return instance;
    }

    public void AddEntry(Entry e, Symbol s, int line) throws DoubleDeclarationException
    {
        if (symbolMap.get(e) != null){
            throw new DoubleDeclarationException(line);
        }

        s.incrementOffset(getVariableZoneSize());
        symbolMap.put(e, s);
    }

    public Symbol Identify(Entry e) throws IdentifiantNonDeclarerException
    {
        if (symbolMap.get(e) == null){
            throw new IdentifiantNonDeclarerException(e.getLine());
        }

        return symbolMap.get(e);
    }

    public int getVariableZoneSize()
    {
        return symbolMap.size();
    }
}
