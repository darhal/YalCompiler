package yal.exceptions;

public class TypeMismatchException extends AnalyseSemantiqueException
{
    public TypeMismatchException(int ligne, String extra_info){
        super(ligne, "TYPE MISMATCH: "+extra_info);
    }

    public TypeMismatchException(int ligne){
        super(ligne, "TYPE MISMATCH: les types d'opérandes ne correspondent pas");
    }
}
