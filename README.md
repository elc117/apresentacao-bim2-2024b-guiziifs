# Herança em Quizzes - Gui

Nesta parte, você vai criar um programa "do zero", escrevendo todo o código. O programa deverá ter uma hierarquia de classes representando diferentes tipos de questões de quizzes, uma classe representando um quiz e um programa principal que irá criar e fazer algumas operações com um quiz.

A herança em Java é uma técnica poderosa para criar hierarquias de classes que compartilham características comuns, permitindo a reutilização eficiente de código.

## Criação da superclasse `Question`

Para a criação dessa classe, foi definido e pensado qual atributo é comum/familiar a qualquer tipo de questão. A resposta final foi: **um enunciado**

``` java
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
```
Por isso, foi criado o atributo `protected String statement`, o qual define um atributo do tipo String que pode ser acessado por qualquer classe filha


## Classes derivadas de `Question`

Após a criação da classe, foi a hora de especializar as questões, visto que existem diferentes tipos. A lógica por trás é de que cada tipo de questão espera receber um tipo de resposta diferente, por exemplo:
- Uma questão de verdadeiro ou falso espera receber V ou F, ou seja, um valor **booleano**
- Uma questão de multipla escolha espera receber uma unica resposta entre as alternativas, o que pode se traduzir como o índice da alternativa, ou seja, um valor **inteiro**
- Uma questão com várias respostas espera receber uma lista com as respostas.

### Questão verdadeiro ou falso

``` java
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
    
```
A questão foi implementada de uma forma padrão e com metodos básicos, com seus geters e seters que executam tarefas simples. Além disso, tempos a utilização da notação `@Override`, ou seja, da sobreescrita de um método com o mesmo nome, tipo e parâmetros herdado da superclasse.

O intuíto de utilizar desse conceito de sobreescrita, foi porque cada questão exige uma forma de exibição do seu enunciado de forma diferente, pois, assim como uma questão de verdadeiro ou falso pode ter apenas uma frase curta como enunciado, uma questão de multipla escolha precisa, necessariamente, das suas alternativas para que faça sentido.

Assim, a utilização da sobreescrita se faz necessario, uma vez que podemos adaptar o método `display` para a ocasião de cada subclasse


### Questão de múltipla escolha
``` java
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

```

Assim como havia dito anteriormente, agora, cada superclasse se adapta para o tipo da questão, então a questão de multipla escolha precisa ter as opções de resposta, em uma `String[]`, com o índice da resposta correta armazenada em 



### Questão de múltipla resposta
``` java
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
```

Por último, a classe de questões de múltipla escolha, contém um vetor de Strings que contém os índices das respostas, produzindo assim, o efeito desejado, de poder esolher mais de uma resposta.

## O quiz

O quiz é práticamente o coração do código, o grande gerenciador dessas classes de questões vai ser essa nova classe chamada `quiz`. Para isso, vamos utilizar o `ArrayList<>` para criar um quiz que contenha a lista de questões

``` java
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

    public void displayAllQuesitons() {
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
```

Essa classe faz operações básicas, como adicionar uma questão no quiz, retornar as questões, mostrar todas as questões, além de gerar uma questão aleatória.

Para o método de gerar uma questão aleatória, é utilizada a classe `Random` que faz parte da biblioteca `java.util` que é usada para gerar números pseudoaleatórios.

As instâncias da classe Random são objetos geradores de números aleatórios, por isso, precisamos construi-la através da linha 

```java
Random random = new Random();
```

Nos permitindo utilizar o objeto random para gerar um número pseudoaleatório.

### Montagem do quiz
Nessa parte, foi utilizado os metodos e as classes construidas anteriormente na main, para construir um quiz
``` java
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
```
Foram construidas 10 perguntas para o quiz ao total

## Operações

Por último foi feito na main uma estrutura que permite executar duas operações, de acordo com os métodos implementados na classe `quiz`
- Mostrar todas as perguntas 
- Mostrar uma pergunta aleatória

```
Escolha uma operação:
1 - Exibir todas as perguntas
2 - Mostrar uma pergunta aleatória
3 - Sair
Opção:
```

Assim, ao executar a primeira opção, o resultado esperado seria esse:

```
1. 
O Sol é uma estrela? (V/F)

2.
Qual é a capital da França?
1. Londres
2. Paris
3. Berlim
4. Roma

3.
A Terra tem dois satélites naturais? (V/F)

4.
Quantos lados tem um triângulo?
1. 2
2. 3
3. 4
4. 5

...
```

e ao executar a segunda operação, o resultado esperado é uma questão aleatória qualquer, como por exemplo:

```
Quantos lados tem um triângulo?
1. 2
2. 3
3. 4
4. 5
```

## Referências
1. [Curso de POO Java - Guanabara](https://www.youtube.com/watch?v=KlIL63MeyMY&list=PLHz_AreHm4dkqe2aR0tQK74m8SFe-aGsY)
2. [Números Aleatórios em Java - A Classe java.util.Random](https://www.devmedia.com.br/numeros-aleatorios-em-java-a-classe-java-util-random/26355)
3. [Entendendo e Aplicadno Herança em Java](https://www.devmedia.com.br/entendendo-e-aplicando-heranca-em-java/24544)
4. [Material da Aula](https://github.com/AndreaInfUFSM/elc117-2024b)
5. Chat GPT - para gerar mais questões para o quiz.