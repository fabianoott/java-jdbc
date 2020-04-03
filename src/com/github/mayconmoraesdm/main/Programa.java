package com.github.mayconmoraesdm.main;

import com.github.mayconmoraesdm.entidades.Contato;
import com.github.mayconmoraesdm.entidades.Entidade;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Programa {

    public static Scanner scanf = new Scanner(System.in);


    public static void main(String[] args) throws SQLException {
        int op = 0;
        boolean validacao = false;


        while (op != 7 && validacao != true){
            System.out.println(" ---------------------------------------------");
            System.out.println("|          Bem Vindo a Agenda eletronica      |");
            System.out.println("|              Escolha uma opção              |");
            System.out.println("|                                             |");
            System.out.println("|   1 - Inserir contato.                      |");
            System.out.println("|   2 - Alterar contato.                      |");
            System.out.println("|   3 - Listar Todos contatos.                |");
            System.out.println("|   4 - Listar contato por ID                 |");
            System.out.println("|   5 - Listar contato por nome               |");
            System.out.println("|   6 - Excluir contato                       |");
            System.out.println("|   7 - Sair                                  |");
            System.out.println(" ---------------------------------------------");
            op = scanf.nextInt();
                validacao = menuPrincipal(op);
        }
    }
    public static boolean menuPrincipal(int op) throws SQLException {
        int id = 0;
        String nome;
        switch (op){
            case 1:
                insere();
                return false;
            case 2:
                menuAlteracao();
                return false;
            case 3:
                listaTodos();
                return false;
            case 4:
                System.out.println("Digite o Id que será listado:");
                id = scanf.nextInt();
                lista(id);
                return false;
            case 5:
                System.out.println("Digite o Nome que será listado:");
                nome = scanf.next();
                listaPornome(nome);
                return false;
            case 6:
                exclui();
                return false;
            case 7:
                System.out.println("Saindo do sistema");
                return true;
            default:
                System.out.println("Opção inválida.");
                return false;
        }
    }

    public static void menuAlteracao() {
        try {
            System.out.println("Digite o id do contato para ser alterado");
            Contato contato = new Contato(scanf.nextInt());
            boolean validacao = false;
            int op = 0;
            while (op != 8 && validacao == false){
                System.out.println(" ---------------------------------------------");
                System.out.println("   ESCOLHA QUAL INFORMAÇÃO SERÁ ALTERADA     ");
                System.out.println("         PARA O CONTATO ABAIXO               ");
                System.out.println(" ---------------------------------------------");
                System.out.println("    Para o contato:"+contato.getId());
                System.out.println("    Nome:"+contato.getNome());
                System.out.println("    Celular 1:"+contato.getCelular());
                System.out.println("    Celular 2:"+contato.getCelular2());
                System.out.println("    Telefone 1:"+contato.getTelefone());
                System.out.println("    Telefone 2:"+contato.getTelefone2());
                System.out.println("    E-mail :"+contato.getEmail());

                System.out.println(" ---------------------------------------------");
                System.out.println("|   1 - Nome                                  |");
                System.out.println("|   2 - Celular 1                             |");
                System.out.println("|   3 - Celular 2                             |");
                System.out.println("|   4 - Telefone 1                            |");
                System.out.println("|   5 - Telefone 2                            |");
                System.out.println("|   6 - E-mail                                |");
                System.out.println("|                                             |");
                System.out.println("|   7 - Salvar                                |");
                System.out.println("|   8 - Cancelar                              |");
                System.out.println(" ---------------------------------------------");
                op = scanf.nextInt();
                validacao = altera(op, contato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean altera(int op, Contato contato) throws SQLException {

        switch (op) {
            case 1:
                System.out.println("Digite o novo nome");
                contato.setNome(scanf.next());
                return false;
            case 2:
                System.out.println("Digite o novo celular 1:");
                contato.setCelular(scanf.next());
                return false;
            case 3:
                System.out.println("Digite o novo celular 2:");
                contato.setCelular2(scanf.next());
                return false;
            case 4:
                System.out.println("Digite o novo telefone 1:");
                contato.setTelefone(scanf.next());
                return false;
            case 5:
                System.out.println("Digite o novo telefone 2:");
                contato.setTelefone2(scanf.next());
                return false;
            case 6:
                System.out.println("Digite o nome e-mail:");
                contato.setEmail(scanf.next());
                return false;
            case 7:
                System.out.println("Contato alterado");
                return contato.altera();
            case 8:
                System.out.println("Operação cancelada");
                return true;
            default:
                System.out.println("Opção inválida.");
                return false;
        }

    }


    public static void listaTodos() throws SQLException {
        System.out.println("Lista Todos");
        Contato contato = new Contato();
        List<Contato> contatos = contato.busca();
        if (contatos.size() == 0) {
            System.out.printf("Nenhum contato na agenda!");
        } else {
            contatos.forEach(c -> System.out.println(String.format("Id: %d\nNome: %s\nTelefone: %s\nTelefone2: %S\nCelular: %s\nCelular2: %s \nE-mail: %s \n",
                    c.getId(), c.getNome(), c.getTelefone(),c.getTelefone2(), c.getCelular(), c.getCelular2(), c.getEmail())));
        }
    }

    public static void lista(Integer id) {
        System.out.println("Lista um contato por ID");
        Contato c = new Contato(id);
        System.out.println(String.format("Id: %d\nNome: %s\nTelefone: %s\nTelefone2: %S\nCelular: %s\nCelular2: \nE-mail: %s \n",
                c.getId(), c.getNome(), c.getTelefone(),c.getTelefone2(), c.getCelular(), c.getCelular2(), c.getEmail()));
    }

    public static void listaPornome(String nome){
        System.out.println("Lista um contato por NOME");
        Contato c = new Contato(nome);
        System.out.println(String.format("Id: %d\nNome: %s\nTelefone: %s\nTelefone2: %S\nCelular: %s\nCelular2: \nE-mail: %s \n",
                c.getId(), c.getNome(), c.getTelefone(),c.getTelefone2(), c.getCelular(), c.getCelular2(), c.getEmail()));
    }





    public static void insere() {
        System.out.println("Inserindo contato");
        try {
            Contato contato = new Contato();

            System.out.println("Digite o Nome: ");
            contato.setNome(scanf.next());
            System.out.println("Digite o Telefone 1: ");
            contato.setTelefone(scanf.next());
            System.out.println("Digite o Telefone 2: ");
            contato.setTelefone2(scanf.next());
            System.out.println("Digite o Celular 1: ");
            contato.setCelular(scanf.next());
            System.out.println("Digite o Celular 2: ");
            contato.setCelular2(scanf.next());
            System.out.println("Digite o E-mail: ");
            contato.setEmail(scanf.next());
            contato.insere();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void exclui() {
        try {
            System.out.println("Digite ID do contato para excluir");
            Contato contato = new Contato(scanf.nextInt());
            contato.exclui();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
