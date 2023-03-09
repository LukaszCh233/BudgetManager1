



import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetFunction {
    ProfExpenList profExpenList = new ProfExpenList();
    // BudgetFunction is class where the functions for the Budget Manager project are performed.

    // addProfit is function which allows the user add Profit to Budget Manager.
    public void addProfit() {
        Scanner scanner = new Scanner(System.in);
        String backMenu;

        do {
            System.out.println("PODAJ NAZWE I KWOTE PRZYCHODU: ");
            try {
                String name = scanner.nextLine();
                while (!scanner.hasNextDouble()) {
                    System.out.println("Niepoprawna kwota. Podaj jeszcze raz: ");
                    scanner.next();
                }
                double amount = scanner.nextDouble();
                scanner.nextLine();
                profExpenList.getList().add(new ProfExpens(name, amount, ProfitExpensType.PROFIT));
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.println("CZY CHCESZ KONTYNUOWAC? TAK/NIE");
            backMenu = scanner.nextLine();
        } while (!backMenu.equalsIgnoreCase("NIE"));
    }

    // addExpenses is function which allows the user add Expenses to Budget Manager.
    public void addExpenses() {
        Scanner scanner = new Scanner(System.in);
        String backMenu;

        do {
            System.out.println("PODAJ NAZWE I KWOTE WYDATKU: ");
            try {
                String name = scanner.nextLine();
                while (!scanner.hasNextDouble()) {
                    System.out.println("Niepoprawna kwota. Podaj jeszcze raz: ");
                    scanner.next();
                }
                double amount = scanner.nextDouble();
                scanner.nextLine();
                profExpenList.getList().add(new ProfExpens(name, amount, ProfitExpensType.EXPENS));
            } catch (Exception d) {
                scanner.nextLine();
            }
            System.out.println("CZY CHCESZ KONTYNUOWAC? TAK/NIE");
            backMenu = scanner.nextLine();
        } while (!backMenu.equalsIgnoreCase("NIE"));
    }

    // ProfExpenView is function which is to be displayed list of profit and expenses, sum of profit and expenses.
    // Check our budget balance also this function can can remove the selected index from the list.
    public void ProfExpenView() {

        Scanner scanner = new Scanner(System.in);
        String backMenu;
        double sumProfit;
        double sumExpens;

        do {
            sumProfit = 0;
            sumExpens = 0;
            System.out.println("LISTA PRZYCHODOW i WYDATKOW: ");

            System.out.println("PRZYCHODY:");
            for (int i = 0; i < profExpenList.list.size(); i++) {
                ProfExpens profitExpens = profExpenList.list.get(i);
                if (profitExpens.getType() == ProfitExpensType.PROFIT) {
                    System.out.println(i + ". " + profitExpens + " zl");
                    sumProfit += profitExpens.amount;
                }
            }
            System.out.println("SUMA PRZYCHODU - " + sumProfit + " zl");
            System.out.println("---------------------------");
            System.out.println("WYDATKI:");
            for (int a = 0; a < profExpenList.list.size(); a++) {
                ProfExpens profitExpens1 = profExpenList.list.get(a);
                if (profitExpens1.getType() == ProfitExpensType.EXPENS) {
                    System.out.println(a + ". " + profitExpens1 + " zl");
                    sumExpens += profitExpens1.amount;
                }
            }
            System.out.println("SUMA WYDATKOW - " + sumExpens + " zl");

            System.out.println("\n##############################\n");

            double balance = sumProfit - sumExpens;
            System.out.println("AKTUALNE SALDO TO:" + balance + " zl\n");
            if (balance < 0) {
                System.out.println("WYDATKI PRZEKROCZYŁY TWÓJ BUDŻET!!!!");
            }
            System.out.println("JESLI CHCESZ USNUNAC COS Z LISTY WYBIERZ /TAK/ JESLI CHCESZ WYJSC WYBIERZ /NIE/:");
            backMenu = scanner.nextLine();

            if (backMenu.equalsIgnoreCase("tak")) {
                try {
                    System.out.println("WYBIERZ NUMER:");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    profExpenList.list.remove(index);
                } catch (InputMismatchException e) {
                    System.out.println("Niepoprawny format numeru, spróbuj ponownie.");
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Nie ma elementu o podanym numerze, spróbuj ponownie.");
                }
            }
        } while (!backMenu.equalsIgnoreCase("nie"));
    }
}
