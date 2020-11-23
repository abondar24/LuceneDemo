package org.abondar.experimental.lucenedemo.command;


import org.abondar.experimental.lucenedemo.command.impl.FileIndexerCommand;
import org.abondar.experimental.lucenedemo.command.impl.IndexTuningCommand;
import org.abondar.experimental.lucenedemo.command.impl.IndexerCommand;
import org.abondar.experimental.lucenedemo.command.impl.PhraseQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.PrefixQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.SearchCommand;
import org.abondar.experimental.lucenedemo.command.impl.SpanNearQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.SpanTermQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.TermQueryCommand;
import org.apache.poi.ss.formula.functions.T;

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

                case PQC:
                    PhraseQueryCommand pqc = new PhraseQueryCommand();
                    executor.executeCommand(pqc);
                    break;

                case PRQC:
                    PrefixQueryCommand prqc = new PrefixQueryCommand();
                    executor.executeCommand(prqc);
                    break;

                case SC:
                    SearchCommand sc = new SearchCommand();
                    executor.executeCommand(sc);
                    break;

                case SNQC:
                    SpanNearQueryCommand snqc = new SpanNearQueryCommand();
                    executor.executeCommand(snqc);
                    break;

                case STQC:
                    SpanTermQueryCommand stqc = new SpanTermQueryCommand();
                    executor.executeCommand(stqc);
                    break;

                case TQC:
                    TermQueryCommand tqc = new TermQueryCommand();
                    executor.executeCommand(tqc);
                    break;

            }

        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
