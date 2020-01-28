package yal.exceptions;

public class DoubleDeclarationException extends AnalyseException
{
    public DoubleDeclarationException(int line)
    {
        super("[ERREUR]: Double detecté déclaration à la ligne: " + line+".\n");
    }
}
