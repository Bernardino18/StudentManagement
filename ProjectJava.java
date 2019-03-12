/*
 * 
 * Developed by Bernardino Sousa and João Silva
 * 
 */
package trabalhojava;

import java.io.*;
//excel
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
//ler e escrever ficheiro
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;

/**
 *
 * @author bfgso
 */
public class TrabalhoJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        final int tamanho = 99;
        String[] turma = new String[tamanho];
        int[] numero = new int[tamanho];
        String[] nome = new String[tamanho];
        double[] alg = new double[tamanho];
        double[] java = new double[tamanho];
        double[] vb = new double[tamanho];
        int[] notafinal = new int[tamanho];
        int nelens = 0, op;
        do{
            op = menu();
            switch(op){
                case 0:
                    JOptionPane.showMessageDialog(null, "<html><h1 style='font-size:20px;color:red;text-align:center'>Adeus..</h1></html>");
                    break;
                case 1:
                    Scanner ficheiro = new Scanner(new File("alunos.txt"));
                    nelens = ler(ficheiro,turma,numero,nome,alg,java,vb,notafinal,nelens);
                    break;
                case 2:
                    if(nelens > 0)
                        listar(turma,numero,nome,alg,java,vb,notafinal,nelens);
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 3:
                    inserir(turma,numero,nome,alg,java,vb,notafinal,nelens);
                    nelens++;
                    break;
                case 4:
                    if(nelens > 0)
                        atualizar(turma, numero, nome, alg, java, vb, notafinal, nelens);
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 5:
                    if(nelens > 0)
                        if(eliminar(turma, numero, nome, alg, java, vb, notafinal, nelens))
                        {
                            JOptionPane.showMessageDialog(null, "Aluno eliminado com sucesso !");
                            nelens--;
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Não existe nenhum aluno com esse numero");
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 6:
                    if(nelens > 0)
                        melhoresalunos(nome,notafinal,nelens);
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 7:
                    if(nelens > 0)
                        classficacao(turma, numero, nome, alg, java, vb, notafinal, nelens);
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 8:
                    if(nelens > 0)
                        maiornomevogais(nome,nelens);
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 9:
                    if(nelens > 0)
                    {
                        Formatter ficheiro_3 = new Formatter(new File("nomestring.txt"));
                        nomeString(ficheiro_3,turma, numero, nome, alg, java, vb, notafinal, nelens);
                    }else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    
                    break;
                case 10:
                    if(nelens > 0)
                    {
                        Formatter ficheiro_2 = new Formatter(new File("alunos.txt"));
                        guardar(ficheiro_2, turma, numero, nome, alg, java, vb, notafinal, nelens);
                    }     
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                case 11:
                    if(nelens > 0)
                        excel(turma, numero, nome, alg, java, vb, notafinal, nelens);
                    else
                        JOptionPane.showMessageDialog(null, "Nao existem alunos");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
                    break;
            }
        }while(op != 0);
    }
    private static void excel(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens)
    {
        try {
            String filename = "excel.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Trabalho_Java"); 
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Turma");
            rowhead.createCell(1).setCellValue("Numero");
            rowhead.createCell(2).setCellValue("Nome");
            rowhead.createCell(3).setCellValue("Nota de Algoritmia");
            rowhead.createCell(4).setCellValue("Nota de Java");
            rowhead.createCell(5).setCellValue("Nota de VB");
            rowhead.createCell(6).setCellValue("Nota Final");
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            for (int x = 0; x < nelens; x++)
            {
                    HSSFRow row = sheet.createRow((short)x+1);
                    row.createCell(0).setCellValue(turma[x]);
                    row.createCell(1).setCellValue(numero[x]);
                    row.createCell(2).setCellValue(nome[x]);
                    row.createCell(3).setCellValue(alg[x]);
                    row.createCell(4).setCellValue(java[x]);
                    row.createCell(5).setCellValue(vb[x]);
                    row.createCell(6).setCellValue(notafinal[x]);
                    sheet.autoSizeColumn(0);
                    sheet.autoSizeColumn(1);
                    sheet.autoSizeColumn(2);
                    sheet.autoSizeColumn(3);
                    sheet.autoSizeColumn(4);
                    sheet.autoSizeColumn(5);
                    sheet.autoSizeColumn(6);
            }
            

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
    private static void classficacao(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens)
    {
        Ordenar_Alf(turma,numero,nome,alg,java,vb,notafinal,nelens);
        Ordenar_Cres(turma,numero,nome,alg,java,vb,notafinal,nelens);
        listar(turma, numero, nome, alg, java, vb, notafinal, nelens);
    }
    public static void guardar(Formatter ficheiro,String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens) {
            for (int i = 0; i < nelens; i++)
            {
                if(i == (nelens - 1))
                    ficheiro.format(Locale.US,"%s_%d_%s_%f_%f_%f_%d",turma[i],numero[i],nome[i],alg[i],java[i],vb[i],notafinal[i]);
                else
                    ficheiro.format(Locale.US,"%s_%d_%s_%f_%f_%f_%d\n",turma[i],numero[i],nome[i],alg[i],java[i],vb[i],notafinal[i]);
            }
            ficheiro.close();
            JOptionPane.showMessageDialog(null, "Gurdado com sucesso no ficheiro alunos.txt");
    }
    
    public static int ler(Scanner ficheiro,String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens) {
        int repetidos = 0;
        while(ficheiro.hasNextLine())
        {
                String[] parte = ficheiro.nextLine().split("_");
                int pos = pesquisar(numero, nelens, Integer.parseInt(parte[1]));
                if(pos == -1)
                {
                    turma[nelens] = parte[0];
                    numero[nelens] = Integer.parseInt(parte[1]);
                    nome[nelens] = parte[2];
                    alg[nelens] = Double.parseDouble(parte[3]);
                    java[nelens] = Double.parseDouble(parte[4]);
                    vb[nelens] = Double.parseDouble(parte[5]);
                    notafinal[nelens] = Integer.parseInt(parte[6]);
                    nelens++;
                }else
                    repetidos++;
                
                
        }
        if(repetidos != 0)
            JOptionPane.showMessageDialog(null, "Encontramos "+ repetidos + " aluno(s) repetidos!");
        if(nelens == 0)
            JOptionPane.showMessageDialog(null, "O ficheiro alunos.txt nao tem qualquer informação");    
        else
            JOptionPane.showMessageDialog(null, "Foram carregados "+ (nelens - repetidos) + " alunos");
            return nelens;
        }
        
    
    private static int menu(){
        int op;
        String item1 = "<html><h1 style='color:red'>Trabalho desenvolvido por Bernardino Sousa e João Silva</h1>\n";
        String item2 = "1.Ler Alunos\n2.Ver\n";
        String item3 = "3.Inserir Aluno\n";
        String item4 = "4.Atualizar Alunos\n";
        String item5 = "5.Apagar Aluno\n";
        String item6 = "6.Melhores Alunos\n";
        String item7 = "7.Classificacao dos Alunos\n";
        String item8 = "8.Aluno(s) com Maior(es) Vogal(is)\n";
        String item9 = "9.Pesquisar Aluno e Guarda Ficheiro\n";
        String item10 = "10.Guardar Alunos\n";
        String item11 = "11.Exportar Alunos para Excel \n";
        String item13 = "0.Sair\n";

        op = Integer.parseInt(JOptionPane.showInputDialog(item1+item2+item3+item4+item5+item6+item7+item8+item9+item10+item11+item13+"Introduzir a opção"));
        return op;
    }
    private static void nomeString(Formatter ficheiro_3 , String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens){
        
       String input;
       int[] posicoesnome = new int[nelens];
       int nelensposicoesnome = 0;
       input = JOptionPane.showInputDialog("Qual é o nome que quer procurar ?");
       for (int i = 0; i < nelens; i++)
       {
            int intIndex = nome[i].indexOf(input);

            if(intIndex != - 1) {
                posicoesnome[nelensposicoesnome] = i;
                nelensposicoesnome++;
            }
       }
       if(nelensposicoesnome == 0)
           JOptionPane.showMessageDialog(null, "Nao existem nenhum nome que introduziu.");
           
       else 
       {
           for (int i = 0; i < nelensposicoesnome; i++)
           {
               JOptionPane.showMessageDialog(null, nome[i]);
               if(i == (nelensposicoesnome - 1))
                   ficheiro_3.format(Locale.US,"---------------\nTurma: %s\nNumero:%d\nNome:%s\nNota de Algoritmia:%f\nNota de Java:%f\nNota de Visual Basic:%f\nNota Final:%d\n---------------",turma[posicoesnome[i]],numero[posicoesnome[i]],nome[posicoesnome[i]],alg[posicoesnome[i]],java[posicoesnome[i]],vb[posicoesnome[i]],notafinal[posicoesnome[i]]);
               else
                   ficheiro_3.format(Locale.US,"---------------\nTurma: %s\nNumero:%d\nNome:%s\nNota de Algoritmia:%f\nNota de Java:%f\nNota de Visual Basic:%f\nNota Final:%d\n---------------\n\n",turma[posicoesnome[i]],numero[posicoesnome[i]],nome[posicoesnome[i]],alg[posicoesnome[i]],java[posicoesnome[i]],vb[posicoesnome[i]],notafinal[posicoesnome[i]]);
           }
                    
       }
       ficheiro_3.close();
       
    }
    
    private static boolean eliminar(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens){
        int num;
        int pos,x;
        num = Integer.parseInt(JOptionPane.showInputDialog("Qual o numero de aluno que pretende eliminar?"));
        pos = pesquisar(numero,nelens,num);
        if(nelens == 0 || pos == -1)
        {
            for(x=pos; x < nelens-1;x++)
            {
                turma[x] = turma[x+1];
                numero[x] = numero[x+1];
                nome[x] = nome[x+1];
                alg[x] = alg[x+1];
                java[x] = java[x+1];
                vb[x] = vb[x+1];
                notafinal[x] = notafinal[x+1];
            }
            return true;
        }else
            return false;
       
    }
    private static void melhoresalunos(String[] nome,int[] notafinal,int nelens){
        Ordenar(notafinal,nome, nelens);
        String msghtml = "<html>" +
"	<table style='border-collapse:collapse'>" +
"		<tr>" +
"			<td style='border-bottom: 1px solid black;'>Nome</td>" +
"			<td style='border-bottom: 1px solid black;'>Nota Final</td>" +
"		</tr><hr>";
        int i = 0;
        while(i < nelens || notafinal[i] > 10)
        {
            msghtml += "<tr>" +
"			<td>" + nome[i] + "</td>" +
"			<td>" + notafinal[i] + "</td>" +
"		</tr>";
            i++;
            
        }
        msghtml += "</table></html>";
        JOptionPane.showMessageDialog(null, msghtml);
            
    }
    public static void maiornomevogais(String[] nome,int nelens) {
        String aux;
        int x , y;
        
        int[] numvogais = new int[99];
        
        
        for(x= 0; x < nelens;x++)
        {
            numvogais[x] = 0;                         
                    
            for(y = 0; y < nome[x].length();y++)
                if(Character.toLowerCase(nome[x].charAt(y)) == 'a' ||
                   Character.toLowerCase(nome[x].charAt(y)) == 'e' ||  
                   Character.toLowerCase(nome[x].charAt(y)) == 'i' ||  
                   Character.toLowerCase(nome[x].charAt(y)) == 'o' ||
                   Character.toLowerCase(nome[x].charAt(y)) == 'u' )
                    numvogais[x] = numvogais[x] + 1;

        }
        int maiorvogal = 0;
        for(x = 0; x < nelens; x++)
        {
            if (numvogais[x] > maiorvogal)
                maiorvogal = numvogais[x];
        }
        String msghtml = "<html>" +
"	<table style='border-collapse:collapse'>" +
"		<tr>" +
"			<td style='border-bottom: 1px solid black;'>Nome</td>" +
"			<td style='border-bottom: 1px solid black;'>Qtd Vogais</td>" +
"		</tr><hr>";
        for(x = 0; x < nelens; x++)
            if(numvogais[x] == maiorvogal)
            {
                msghtml += "<tr>" +
"			<td>" + nome[x] + "</td>" +
"			<td>" + numvogais[x] + "</td>" +
"		</tr>"; 
            }
            
        msghtml += "</table></html>";
        JOptionPane.showMessageDialog(null, msghtml);
    }
    public static void Ordenar_Alf(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens) {
        String aux,aux2;
        int aux3,aux7;
        double aux4,aux5,aux6;
        int x , y;
        for (x = 0; x < nelens - 1;x++)
            for (y = x+1; y < nelens ; y++)
                if(nome[y].compareToIgnoreCase(nome[x])< 0)
                {
                    aux = nome[x];
                    aux2 = turma[x];
                    aux3 = numero[x];
                    aux4 = alg[x];
                    aux5 = java[x];
                    aux6 = vb[x];
                    aux7 = notafinal[x];
                    
                    
                    nome[x] = nome[y];
                    turma[x] = turma[y];
                    numero[x] = numero[y];
                    alg[x] = alg[y];
                    java[x] = java[y];
                    vb[x] = vb[y];
                    notafinal[x] = notafinal[y];
                    
                    
                    
                    nome[y] = aux;
                    turma[y] = aux2;
                    numero[y] = aux3;
                    alg[y] = aux4;
                    java[y] = aux5;
                    vb[y] = aux6;
                    notafinal[y] = aux7;
                    
                }
        
        
        
    }
    public static void Ordenar_Cres(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens) {
        String aux,aux2;
        int aux3,aux7;
        double aux4,aux5,aux6;
        int x , y;
        for (x = 0; x < nelens - 1;x++)
            for (y = x+1; y < nelens ; y++)
                if(notafinal[y] < notafinal[x])
                {
                    aux = nome[x];
                    aux2 = turma[x];
                    aux3 = numero[x];
                    aux4 = alg[x];
                    aux5 = java[x];
                    aux6 = vb[x];
                    aux7 = notafinal[x];
                    
                    
                    nome[x] = nome[y];
                    turma[x] = turma[y];
                    numero[x] = numero[y];
                    alg[x] = alg[y];
                    java[x] = java[y];
                    vb[x] = vb[y];
                    notafinal[x] = notafinal[y];
                    
                    
                    
                    nome[y] = aux;
                    turma[y] = aux2;
                    numero[y] = aux3;
                    alg[y] = aux4;
                    java[y] = aux5;
                    vb[y] = aux6;
                    notafinal[y] = aux7;
                }
    }
    public static void Ordenar(int notafinal[],String[] nome,int nelens) {
        int aux;
        String aux2;
        int x , y;
        for (x = 0; x < nelens - 1;x++)
            for (y = x+1; y < nelens ; y++)
                if(notafinal[y] > notafinal[x])
                {
                    aux = notafinal[x];
                    aux2 = nome[x];
                    notafinal[x] = notafinal[y];
                    nome[x] = nome[y];
                    notafinal[y] = aux;
                    nome[y] = aux2;
                }
    }
    
    private static void inserir(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens){
        do{
            nome[nelens] = JOptionPane.showInputDialog("Introduza o nome do aluno"); 
        }while(nome[nelens] == null || nome[nelens] == "");
        turma[nelens] = JOptionPane.showInputDialog("Introduza a turma do aluno "+nome[nelens]);
        do{
            numero[nelens] = Integer.parseInt(JOptionPane.showInputDialog("Introduza o numero do aluno " +nome[nelens]));
            if(numero[nelens] < 0)
               JOptionPane.showMessageDialog(null, "Numero do aluno Inválido");
        }while(numero[nelens] < 0);
        do {
           alg[nelens] = Double.parseDouble(JOptionPane.showInputDialog("Introduza a nota em Algoritmia do aluno " + nome[nelens])); 
           if(alg[nelens] < 0 || alg[nelens] > 20)
               JOptionPane.showMessageDialog(null, "Nota de Algoritmia Inválida");
        }
        while (alg[nelens] > 20 || alg[nelens] < 0); 
        
        do {
           java[nelens] = Double.parseDouble(JOptionPane.showInputDialog("Introduza a nota em Java do aluno " + nome[nelens])); 
           if(java[nelens] < 0 || java[nelens] > 20)
               JOptionPane.showMessageDialog(null, "Nota de Java Inválida");
        }
        while (java[nelens] > 20 || java[nelens] < 0);
        
        do {
           vb[nelens] = Double.parseDouble(JOptionPane.showInputDialog("Introduza a nota em Visual Basic do aluno " + nome[nelens])); 
           if(vb[nelens] < 0 || vb[nelens] > 20)
               JOptionPane.showMessageDialog(null, "Nota de VB Inválida");
        }
        while (vb[nelens] > 20 || vb[nelens] < 0);

        notafinal[nelens] = (int) ((alg[nelens] * 0.3) + (java[nelens] * 0.4) + (vb[nelens] * 0.3));
       
    }
    private static void listar(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens)
    {
        String msghtml = "<html>" +
"	<table style='border-collapse:collapse'>" +
"		<tr>" +
"			<td style='border-bottom: 1px solid black;'>Turma</td>" +
"			<td style='border-bottom: 1px solid black;'>Número</td>" +
"			<td style='border-bottom: 1px solid black;'>Nome</td>" +
"			<td style='border-bottom: 1px solid black;'>Algoritmia</td>" +
"			<td style='border-bottom: 1px solid black;'>Java</td>" +
"			<td style='border-bottom: 1px solid black;'>VB</td>" +
"			<td style='border-bottom: 1px solid black;'>Final</td>" +
"		</tr><hr>";
        for(int i = 0; i < nelens; i++) 
            msghtml += "<tr>" +
"			<td>" + turma[i] + "</td>" +
"			<td>" + numero[i] + "</td>" +
"			<td>" + nome[i] + "</td>" +
"			<td>" + alg[i] + "</td>" +
"			<td>" + java[i] + "</td>" +
"			<td>" + vb[i] + "</td>" +
"			<td>" + notafinal[i] + "</td>" +
"		</tr>";
        msghtml += "</table></html>";
        JOptionPane.showMessageDialog(null, msghtml);
        
    }
    
    private static int menu_atualizar(){
        int op;
        String item1 = "1.Atualizar Turma\n";
        String item2 = "2.Atualizar Numero\n";
        String item3 = "3.Atualizar Nome\n";
        String item4 = "4.Atualizar Nota de Algoritmia\n";
        String item5 = "5.Atualizar Nota de JAVA\n";
        String item6 = "6.Atualizar Nota de Visual Basic\n";

        op = Integer.parseInt(JOptionPane.showInputDialog(item1+item2+item3+item4+item5+item6+"Introduzir a opção"));
        return op;
    }
    private static void atualizar(String[] turma,int[] numero,String[] nome,double[] alg,double[] java,double[] vb,int[] notafinal,int nelens)
    {
        int num,pos;
        num = Integer.parseInt(JOptionPane.showInputDialog("Qual o numero do aluno que deseja alterar as suas informações ?"));
        pos = pesquisar(numero,nelens,num);
        if(pos != -1)
        {
            int op = menu_atualizar();
            switch(op){
                case 1:
                    String turma_tmp = JOptionPane.showInputDialog("Introduza a nova turma");
                    turma[pos] = turma_tmp;
                    break;
                case 2:
                    int numero_tmp = Integer.parseInt(JOptionPane.showInputDialog("Introduza o novo numero"));
                    numero[pos] = numero_tmp;
                    break;
                case 3:
                    String nome_tmp = JOptionPane.showInputDialog("Introduza o novo nome");
                    nome[pos] = nome_tmp;
                    break;
                case 4:
                    double alg_tmp = Double.parseDouble(JOptionPane.showInputDialog("Introduza a nova nota de algoritmia"));
                    alg[pos] = alg_tmp;
                    notafinal[pos] = (int) ((alg[pos] * 0.3) + (java[pos] * 0.4) + (vb[pos] * 0.3));
                    break;
                case 5:
                    double java_tmp = Double.parseDouble(JOptionPane.showInputDialog("Introduza a nova nota de JAVA"));
                    java[pos] = java_tmp;
                    notafinal[pos] = (int) ((alg[pos] * 0.3) + (java[pos] * 0.4) + (vb[pos] * 0.3));
                    break;
                case 6:
                    double vb_tmp = Double.parseDouble(JOptionPane.showInputDialog("Introduza a nova nota de Visual Basic"));
                    vb[pos] = vb_tmp;
                    notafinal[pos] = (int) ((alg[pos] * 0.3) + (java[pos] * 0.4) + (vb[pos] * 0.3));
                    break;
                    
            }
        }
        else
            JOptionPane.showMessageDialog(null,"Nao há qualquer aluno com esse numero introduzido");
        
    }
    private static int pesquisar(int[] numeros,int nelens, int numero)
    {
        if(nelens == 0)
            return -1;
        int pos = 0;
        while(pos < nelens && numero != numeros[pos])
            pos++;
        if(pos < nelens)
            return pos;
        else 
            return -1;
    }
}
