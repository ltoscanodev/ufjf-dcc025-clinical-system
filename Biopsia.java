import java.util.Date;

/**
 * Write a description of class Biopsia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Biopsia extends Exame
{
    public Biopsia(Tecnico tecnico, Paciente paciente, Date dataInicio)
    {
        super(tecnico, paciente, dataInicio, 20);
    }
}
