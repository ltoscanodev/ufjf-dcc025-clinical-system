package Exceptions;


/**
 * Write a description of class ReflectionClassNotFoundException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReflectionClassNotFoundException extends ReflectionException
{
    public ReflectionClassNotFoundException(String className)
    {
        super(String.format("Erro ao instanciar um objeto: Classe: %s", className));
    }
}
