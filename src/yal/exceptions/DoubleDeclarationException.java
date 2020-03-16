package yal.exceptions;

public class DoubleDeclarationException extends AnalyseSemantiqueException
{
    public DoubleDeclarationException(int line)
    {
        super(line, "Double déclaration detecté");
    }
}
