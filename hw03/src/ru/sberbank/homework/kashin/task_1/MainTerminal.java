package ru.sberbank.homework.kashin.task_1;

import ru.sberbank.homework.kashin.task_1.model.Terminal;
import ru.sberbank.homework.kashin.task_1.model.TerminalImpl;
import ru.sberbank.homework.kashin.task_1.writer.ConsoleWriter;
import ru.sberbank.homework.kashin.task_1.writer.Writer;

public class MainTerminal {
    Terminal terminal = new TerminalImpl();
    Writer writer = new ConsoleWriter();

    public long checkBalance() {
        long result = 0;
        try {
            result = terminal.checkBalance();
            writer.print(String.format("Ваш баланс %s", result));
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
        return result;
    }

    void putMoney(long money) {
        try {
            terminal.putMoney(money);
            writer.print(String.format("Вы положили %s на ваш счет.", money));
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
    }

    long withdrawMoney(long money) {
        long result = 0;
        try {
            result = terminal.withdrawMoney(money);
            writer.print(String.format("Вы сняли %s с вашего счета.", result));
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
        return result;
    }

    void enterPin(String pin) {
        try {
            if (terminal.enterPin(pin)) {
                writer.print("Пин введен верно");
            } else {
                writer.print("Неверный пин.");
            }
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainTerminal mainTerminal = new MainTerminal();
        try {
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            mainTerminal.enterPin("7812");
            mainTerminal.checkBalance();
            Thread.sleep(1500);
            mainTerminal.checkBalance();
            Thread.sleep(1500);
            mainTerminal.enterPin("0000");
            mainTerminal.checkBalance();
            Thread.sleep(2100);
            mainTerminal.checkBalance();
            mainTerminal.enterPin("0000");
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(152);
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(200);
            mainTerminal.checkBalance();
            mainTerminal.putMoney(45);
            mainTerminal.checkBalance();
            mainTerminal.putMoney(100);
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(100000);
            mainTerminal.checkBalance();
            mainTerminal.withdrawMoney(900);
            mainTerminal.checkBalance();
            mainTerminal.checkBalance();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
