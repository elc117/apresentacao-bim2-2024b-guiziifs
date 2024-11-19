import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        quiz quiz = new quiz();

        // Pergunta 1: Verdadeiro/Falso
        trueFalse tfQuestion = new trueFalse();
        tfQuestion.setStatement("O Sol é uma estrela?");
        tfQuestion.setAnswer(true);
        quiz.addQuestion(tfQuestion);

        // Pergunta 2: Múltipla Escolha
        multipleChoice mcQuestion = new multipleChoice();
        mcQuestion.setStatement("Qual é a capital da França?");
        mcQuestion.setOptions(new String[]{"Londres", "Paris", "Berlim", "Roma"});
        mcQuestion.setAnswer(2); // Resposta correta: Paris
        quiz.addQuestion(mcQuestion);

        // Pergunta 3: Verdadeiro/Falso
        trueFalse tfQuestion2 = new trueFalse();
        tfQuestion2.setStatement("A Terra tem dois satélites naturais?");
        tfQuestion2.setAnswer(false); // Resposta correta: falso
        quiz.addQuestion(tfQuestion2);

        // Pergunta 4: Múltipla Escolha
        multipleChoice mcQuestion2 = new multipleChoice();
        mcQuestion2.setStatement("Quantos lados tem um triângulo?");
        mcQuestion2.setOptions(new String[]{"2", "3", "4", "5"});
        mcQuestion2.setAnswer(2); // Resposta correta: 3
        quiz.addQuestion(mcQuestion2);

        // Pergunta 5: Múltiplas Respostas
        multipleAnswers maQuestion = new multipleAnswers();
        maQuestion.setStatement("Quais destas são linguagens de programação?");
        maQuestion.setOptions(new String[]{"Java", "Python", "C++", "HTML", "Inglês"});
        maQuestion.setAnswer(new String[]{"Java", "Python", "C++"});
        quiz.addQuestion(maQuestion);

        // Pergunta 6: Verdadeiro/Falso
        trueFalse tfQuestion3 = new trueFalse();
        tfQuestion3.setStatement("A Lua é feita de queijo?");
        tfQuestion3.setAnswer(false);
        quiz.addQuestion(tfQuestion3);

        // Pergunta 7: Múltipla Escolha
        multipleChoice mcQuestion3 = new multipleChoice();
        mcQuestion3.setStatement("Qual é o maior planeta do Sistema Solar?");
        mcQuestion3.setOptions(new String[]{"Terra", "Júpiter", "Marte", "Saturno"});
        mcQuestion3.setAnswer(2); // Resposta correta: Júpiter
        quiz.addQuestion(mcQuestion3);

        // Pergunta 8: Verdadeiro/Falso
        trueFalse tfQuestion4 = new trueFalse();
        tfQuestion4.setStatement("O oceano Pacífico é o maior oceano do mundo?");
        tfQuestion4.setAnswer(true);
        quiz.addQuestion(tfQuestion4);

        // Pergunta 9: Múltipla Escolha
        multipleChoice mcQuestion4 = new multipleChoice();
        mcQuestion4.setStatement("Quantos continentes existem no mundo?");
        mcQuestion4.setOptions(new String[]{"5", "6", "7", "8"});
        mcQuestion4.setAnswer(3); // Resposta correta: 7
        quiz.addQuestion(mcQuestion4);

        // Pergunta 10: Múltiplas Respostas
        multipleAnswers maQuestion2 = new multipleAnswers();
        maQuestion2.setStatement("Quais destas são frutas?");
        maQuestion2.setOptions(new String[]{"Maçã", "Tomate", "Batata", "Pera", "Cenoura"});
        maQuestion2.setAnswer(new String[]{"Maçã", "Tomate", "Pera"});
        quiz.addQuestion(maQuestion2);


        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Exibir todas as perguntas");
            System.out.println("2 - Mostrar uma pergunta aleatória");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    quiz.displayAllQuestions();
                    break;
                case 2:
                    System.out.println();
                    quiz.randomQuestion();
                    break;
                case 3:
                    running = false;
                    System.out.println("Encerrando o programa. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }


}
