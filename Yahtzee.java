import java.util.*;
import java.util.stream.Collectors;

public class Yahtzee {
    public static final List<String> SCORES = Arrays.asList(
        "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes",
        "Three of a Kind", "Four of a Kind", "Full House",
        "Small Straight", "Large Straight", "Yahtzee", "Chance"
    );

    public static final YahtzeeDice DICE = new YahtzeeDice();
    public static boolean RIG=false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Yahtzee!\nHow many players? ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            if(name.equals("Rig")){
                RIG=true;
            }
            players.add(new HumanPlayer(name));
        }

        Scoreboard scoreboard = new Scoreboard(players.stream().map(Player::getName).collect(Collectors.toList()));

        for (int round = 0; round < 13; round++) {
            for (Player player : players) {
                player.takeTurn(scoreboard);
            }
        }

        System.out.println(scoreboard);
    }
}

class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}

class Scoreboard {
    Map<String, List<Integer>> board = new HashMap<>();
    List<String> names;

    public Scoreboard(List<String> players) {
        for (String score : Yahtzee.SCORES) {
            List<Integer> scores = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                scores.add(0);
            }
            board.put(score, scores);
        }
        this.names = players;
    }

    public static List<Pair<String, Integer>> classify() {
        List<Pair<String, Integer>> ret = new ArrayList<>();
        Map<Integer, Long> occurrences = Yahtzee.DICE.occurrences();
        int maxOccurrences = occurrences.values().stream().max(Long::compareTo).orElse(0L).intValue();

        if (maxOccurrences == 5) {
            ret.add(new Pair<>("Yahtzee", 50));
            ret.add(new Pair<>("Four of a Kind", Yahtzee.DICE.sumDice()));
            ret.add(new Pair<>("Three of a Kind", Yahtzee.DICE.sumDice()));
        } else if (maxOccurrences == 4) {
            ret.add(new Pair<>("Four of a Kind", Yahtzee.DICE.sumDice()));
            ret.add(new Pair<>("Three of a Kind", Yahtzee.DICE.sumDice()));
        } else if (maxOccurrences == 3) {
            if (occurrences.values().stream().filter(x -> x == 2).count() == 1) {
                ret.add(new Pair<>("Full House", 25));
            } else {
                ret.add(new Pair<>("Three of a Kind", Yahtzee.DICE.sumDice()));
            }
        }

        if (isSmallStraight(occurrences)) {
            ret.add(new Pair<>("Small Straight", 30));
        } else if (isLargeStraight(occurrences)) {
            ret.add(new Pair<>("Large Straight", 40));
        }
        if (occurrences.getOrDefault(1, 0L) > 0) {
            ret.add(new Pair<>("Ones", occurrences.get(1).intValue()));
        }
        if (occurrences.getOrDefault(2, 0L) > 0) {
            ret.add(new Pair<>("Twos", occurrences.get(2).intValue() * 2));
        }
        if (occurrences.getOrDefault(3, 0L) > 0) {
            ret.add(new Pair<>("Threes", occurrences.get(3).intValue() * 3));
        }
        if (occurrences.getOrDefault(4, 0L) > 0) {
            ret.add(new Pair<>("Fours", occurrences.get(4).intValue() * 4));
        }
        if (occurrences.getOrDefault(5, 0L) > 0) {
            ret.add(new Pair<>("Fives", occurrences.get(5).intValue() * 5));
        }
        if (occurrences.getOrDefault(6, 0L) > 0) {
            ret.add(new Pair<>("Sixes", occurrences.get(6).intValue() * 6));
        }
        ret.add(new Pair<>("Chance", Yahtzee.DICE.sumDice()));
        return ret;
    }

    public void update(String player, String category, int score) {
        System.out.println(player + " " + category + " " + score);
        int index = names.indexOf(player);
        List<Integer> scores = board.get(category);
        if (scores != null) {
            scores.set(index, score);
        } else {
            System.out.println("Category not found: " + category);
        }
    }

    public boolean valid(String player, String category) {
        int index = names.indexOf(player);
        List<Integer> scores = board.get(category);
        return scores != null && scores.get(index) == 0;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("|--------------------|");
        for (String name : names) {
            output.append(String.format("%-1os|", name));
        }
        output.append("\n");
        for (Map.Entry<String, List<Integer>> entry : board.entrySet()) {
            output.append(String.format("|%20s|", entry.getKey()));
            for (int score : entry.getValue()) {
                output.append(String.format("%-10d|", score));
            }
            output.append("\n");
        }
        output.append("|--------------------|");
        for (String name : names) {
            output.append("----------|");
        }
        return output.toString();
    }

    private static boolean isSmallStraight(Map<Integer, Long> occurrences) {
        return (occurrences.containsKey(1) && occurrences.containsKey(2) && occurrences.containsKey(3) && occurrences.containsKey(4))
                || (occurrences.containsKey(2) && occurrences.containsKey(3) && occurrences.containsKey(4) && occurrences.containsKey(5))
                || (occurrences.containsKey(3) && occurrences.containsKey(4) && occurrences.containsKey(5) && occurrences.containsKey(6));
    }

    private static boolean isLargeStraight(Map<Integer, Long> occurrences) {
        return (occurrences.containsKey(1) && occurrences.containsKey(2) && occurrences.containsKey(3) && occurrences.containsKey(4) && occurrences.containsKey(5))
                || (occurrences.containsKey(2) && occurrences.containsKey(3) && occurrences.containsKey(4) && occurrences.containsKey(5) && occurrences.containsKey(6));
    }

    public int calcScore(String choice) {
        List<Pair<String, Integer>> classifications = classify();
        for (Pair<String, Integer> pair : classifications) {
            if (pair.getKey().equals(choice)) {
                return pair.getValue();
            }
        }
        return 0;
    }

    int getScore(String name, String choice) {
        if (!board.containsKey(choice)) {
            return 0;
        }
        return board.get(choice).get(names.indexOf(name));
    }

    boolean isValid(String choice) {
        for (Pair<String, Integer> pair : classify()) {
            if (pair.getKey().equals(choice)) {
                return true;
            }
        }
        return false;
    }
}

interface Player {
    String getName();
    void takeTurn(Scoreboard scoreboard);
}

class HumanPlayer implements Player {
    private final String name;

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void takeTurn(Scoreboard scoreboard) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + "'s turn");
        System.out.println("Press enter to roll the dice");
        scanner.nextLine();

        Yahtzee.DICE.roll();
        System.out.println(Yahtzee.DICE);

        for (int i = 0; i < 2; i++) {
            while(true){
            System.out.print("Enter the index of the dice you want to keep, or c to continue: ");
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("c")) {
                break;
            }
            try {
                int index = Integer.parseInt(input.trim()) - 1;
                Yahtzee.DICE.toggle(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
            System.out.println(Yahtzee.DICE);
        }
            Yahtzee.DICE.roll();
            System.out.println(Yahtzee.DICE);
        }

        System.out.println(scoreboard);
        System.out.println("Options are: " + scoreboard.classify());

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if (scoreboard.isValid(choice)&&scoreboard.getScore(name, choice)==0) {
                int score = scoreboard.calcScore(choice);
                scoreboard.update(name, choice, score);
                validInput = true;
            } else {
                System.out.println("Invalid choice");
            }
        }
        Yahtzee.DICE.reset();
    }
}

class YahtzeeDice {
    private final List<Die> dice;
    private final List<Boolean> keep;

    public YahtzeeDice() {
        this.dice = Arrays.asList(new Die(), new Die(), new Die(), new Die(), new Die());
        this.keep = Arrays.asList(false, false, false, false, false);
    }

    public void roll() {
        if(Yahtzee.RIG){
            Scanner sc=new Scanner(System.in);
            for (int i = 0; i < dice.size(); i++) {
                dice.get(i).rig(sc.nextInt());
            }
            return;
        }
        for (int i = 0; i < dice.size(); i++) {
            if (!keep.get(i)) {
                dice.get(i).roll();
            }
        }
    }

    public List<Integer> getDiceValues() {
        List<Integer> values = new ArrayList<>();
        for (Die die : dice) {
            values.add(die.getCurrentValue());
        }
        return values;
    }

    public int sumDice() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.getCurrentValue();
        }
        return sum;
    }

    public void toggle(int index) {
        if (index >= 0 && index < keep.size()) {
            keep.set(index, !keep.get(index));
        } else {
            throw new IllegalArgumentException("Illegal Input");
        }
    }

    public Map<Integer, Long> getOccurrences() {
        Map<Integer, Long> occurrences = new HashMap<>();
        for (Die die : dice) {
            occurrences.put(die.getCurrentValue(), occurrences.getOrDefault(die.getCurrentValue(), 0L) + 1);
        }
        return occurrences;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < dice.size(); i++) {
            out.append(String.format("%s%s ", keep.get(i) ? "keep  " : "      ", dice.get(i).getCurrentValue()));
        }
        return out.toString();
    }

    Map<Integer, Long> occurrences() {
        return dice.stream().collect(Collectors.groupingBy(Die::getCurrentValue, Collectors.counting()));
    }

    public void reset() {
        for (int i = 0; i < keep.size(); i++) {
            keep.set(i, false);
        }
    }
}

class Die {
    private static final Random RNG = new Random();
    private int currentValue;

    public Die() {
        this.currentValue = RNG.nextInt(6) + 1;
    }

    public void roll() {
        this.currentValue = RNG.nextInt(6) + 1;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void rig(int val){
        this.currentValue=val;
    }
}

