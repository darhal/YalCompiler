package yal.exceptions;

public class IdentifiantNonDeclarerException extends AnalyseException
{
    public IdentifiantNonDeclarerException(){
        super("[ERREUR]: Identifiant non declaré.");
    }
}
