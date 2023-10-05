package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Task;
import eggybyte.ip.data.task.Todo;

/**
 * Command for Marking an Existing Task as Done.
 *
 * @see #COMMAND_WORD
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    protected static final int validArgumentAmount = 1;
    private final int index;

    /**
     * Create a new Command.
     *
     * @param arguments The specifieds arguments will be used for creating command,
     *                  it will automatically check whethere the arguments are
     *                  valid.
     * @see #validArgumentAmount
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public UnmarkCommand(String[] arguments) throws TipsException {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        index = Integer.parseInt(arguments[0]) - 1;
    }

    @Override
    public String customFunction() {
        Task task = runningState.tasks.get(index);
        if (task instanceof Todo) {
            ((Todo) task).isDone = false;
        }
        return task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        return new CommandResult(
                "OK, I've marked this task as not done yet:\n  "
                        + content);
    }
}
