package yal.exceptions;

public class InvalidArgumentException extends AnalyseSemantiqueException
{
    public InvalidArgumentException(int ligne, String m) {
        super(ligne, m);
    }
}
