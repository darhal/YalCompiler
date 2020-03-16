package yal.exceptions;

public class IdentifiantNonDeclarerException extends AnalyseSemantiqueException
{
    public IdentifiantNonDeclarerException(int ligne, String idf){
        super(ligne, "'"+idf+"' -> Identifiant non declaré");
    }
}
