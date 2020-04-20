package yal.declaration.entries;

import yal.declaration.Decltype;

public class ArrayEntry extends Entry
{
    public ArrayEntry(String idf, int line) {
        super(idf, Decltype.ARRAY, line);
    }
}
