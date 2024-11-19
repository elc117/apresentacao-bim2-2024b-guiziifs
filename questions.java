import java.util.ArrayList;
import java.util.Random;

class question {
    protected String statement;

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return this.statement;
    }
        
    public void display() {
        System.out.println(this.statement);
    }

    public question() {
    }
}

class trueFalse extends question {
    private boolean answer;

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean getAnswer() {
        return this.answer;
    }
    
    public void checkAnswer(boolean answer) {
        if (this.answer == answer) {
            System.out.println("Que beleza, resposta certa");
        } else {
            System.out.println("Hmm, ta errado, considere estudar mais");
        }
    }
    
    @Override
    public void display() {
        System.out.println(getStatement() + " (V/F)");
    }

    public trueFalse() {
        this.answer = false;
    }
}

class multipleChoice extends question {
    private String[] options;
    private int CorrectAnswer; //indice da resposta

    public void setAnswer(int Correctanswer) {
        this.CorrectAnswer = Correctanswer;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAnswer() {
        return this.CorrectAnswer;
    }

    public String[] getOptions() {
        return this.options;
    }

    public void checkAnswer(int answer) {
        if (this.CorrectAnswer == answer) {
            System.out.println("Resposta certa! Parabéns!");
        } else {
            System.out.println("Resposta errada, que pena");
        }
    }

    @Override
    public void display() {
        System.out.println(getStatement());
        for (int i = 0; i < options.length; i++) {
            System.out.println((1 + i) + ". " + options[i]);
        }
    }

    public multipleChoice() {
    }

}

class multipleAnswers extends question {
    private String[] options;
    private String[] CorrectAnswers; //contem os indices das respostas

    public void setAnswer(String[] CorrectAnswers) {
        this.CorrectAnswers = CorrectAnswers;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getAnswer() {
        return this.CorrectAnswers;
    }

    public String[] getOptions() {
        return this.options;
    }

    public void checkAnswer(String answer) {
        String[] userAnswers = answer.split(",");
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = userAnswers[i].trim().toLowerCase();
        }

        if (userAnswers.length != CorrectAnswers.length) {
            System.out.println("Resposta errada");
        }

        for (String CorrectAnswers : CorrectAnswers) {
            boolean found = false;
            for (String userAnswer : userAnswers) {
                if (CorrectAnswers.toLowerCase().equals(userAnswer)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Resposta errada");
            }
        }
        System.out.println("Resposta Correta!");
    }

    @Override
    public void display() {
        System.out.println(getStatement());
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
    public multipleAnswers() {
    }
}


class quiz {
    private ArrayList<question> questions;

    public quiz() {
        questions = new ArrayList<>();
    }
    
    public void addQuestion(question question) {
        questions.add(question);
    }

    public ArrayList<question> getQuestions() {
        return this.questions;
    }

    public void displayAllQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". ");
            questions.get(i).display();
            System.out.println();
        }
    }

    public void displayQuestionAtIndex(int index) {
        if (index < 0 || index >= questions.size()) {
            System.out.println("Índice inválido. Por favor, forneça um valor entre 0 e " + (questions.size() - 1));
            return;
        }
        questions.get(index).display(); // Exibe a questão no índice fornecido
    }

    public void randomQuestion() {
        if (questions.isEmpty()) {
            System.out.println("Não possui nenhuma questão disponível");
        }
        Random random = new Random();
        int index = random.nextInt((questions.size()));
        displayQuestionAtIndex(index);
    }

    
}