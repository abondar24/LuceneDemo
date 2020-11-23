package org.abondar.experimental.lucenedemo.command;


import org.abondar.experimental.lucenedemo.command.impl.IndexerCommand;

public class CommandSwitcher {

    private final CommandExecutor executor;

    public CommandSwitcher() {
        this.executor = new CommandExecutor();
    }


    public void executeCommand(String cmd){
        try {
            switch (Commands.valueOf(cmd)){

                case IC:
                    IndexerCommand ic = new IndexerCommand();
                    executor.executeCommand(ic);
                    break;

            }

        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
