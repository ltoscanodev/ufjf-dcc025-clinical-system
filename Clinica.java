
/**
 * Write a description of class Clinica here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Date;

public class Clinica
{
    private final String AGENDA_FILE_PATH = "Dados/agenda.txt";

    private ArrayList<Funcionario> funcionarioList;
    
    public Clinica()
    {
        this.funcionarioList = new ArrayList<Funcionario>();
    }
    
    public void cadastrarFuncionario(Funcionario funcionario)
    {
        if(!funcionarioList.contains(funcionario))
        {
            this.funcionarioList.add(funcionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        }
        else
            System.out.println("Funcionário já está cadastrado!");
    }
    
    public void agendarByFile(String filePath)
    {
        FileHelper fileHelp = new FileHelper();
        
        ArrayList<Servico> servicoList = fileHelp.readFile(filePath);
        
        for(Servico servico : servicoList)
        {
            if(servico instanceof Consulta)
            {
                Medico medico = this.getMedicoMinServicoByAgenda(servico.getDataInicio());
                
                if(medico != null)
                {
                    servico.setFuncionario(medico);
                    medico.addServico(servico.getDataInicio(), servico);
                }
            }
            else if(servico instanceof Exame)
            {
                Tecnico tecnico = this.getTecnicoMinServicoByAgenda(servico.getDataInicio());
                
                if(tecnico != null)
                {
                    servico.setFuncionario(tecnico);
                    tecnico.addServico(servico.getDataInicio(), servico);
                }
            }
        }
                
    }

    public Medico getMedicoMinServicoByAgenda(Date date)
    {
        return (Medico)this.getFuncionarioMinServicoByAgenda(date, Medico.class);
    }
    
    public Tecnico getTecnicoMinServicoByAgenda(Date date)
    {
        return (Tecnico)this.getFuncionarioMinServicoByAgenda(date, Tecnico.class);
    }
    
    public void saveAgenda()
    {
        this.saveAgenda(AGENDA_FILE_PATH);
    }
    
    public void saveAgenda(String filePath)
    {
        FileHelper helper = new FileHelper();
        
        if(helper.writeFile(filePath, funcionarioList))
            System.out.println("Agenda da clínica salva com sucesso!");
    }
    
    private Funcionario getFuncionarioMinServicoByAgenda(Date date, Class filter)
    {
        Funcionario min = null;
        
        for(Funcionario f : funcionarioList)
        {
            if(f.getClass().isAssignableFrom(filter))
            {
                if(min != null)
                {
                    if(f.getServicoListCount(date) < min.getServicoListCount(date))
                        min = f;
                }
                else
                    min = f;
            }
        }
        
        return min;
    }
}
