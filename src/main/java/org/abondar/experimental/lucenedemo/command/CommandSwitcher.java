package org.abondar.experimental.lucenedemo.command;


import org.abondar.experimental.lucenedemo.command.impl.FileIndexerCommand;
import org.abondar.experimental.lucenedemo.command.impl.IndexTuningCommand;
import org.abondar.experimental.lucenedemo.command.impl.IndexerCommand;

public class CommandSwitcher {

    private final CommandExecutor executor;

    public CommandSwitcher() {
        this.executor = new CommandExecutor();
    }


    public void executeCommand(String cmd){
        try {
            switch (Commands.valueOf(cmd)){

                case FIC:
                    FileIndexerCommand fic = new FileIndexerCommand();
                    executor.executeCommand(fic);
                    break;


                case IC:
                    IndexerCommand ic = new IndexerCommand();
                    executor.executeCommand(ic);
                    break;

                case ITC:
                    IndexTuningCommand itc = new IndexTuningCommand();
                    executor.executeCommand(itc);
                    break;

            }

        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
