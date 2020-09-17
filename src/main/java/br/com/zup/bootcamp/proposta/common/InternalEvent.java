package br.com.zup.bootcamp.proposta.common;

public abstract class InternalEvent {

    private Exception exception;
    private long startTime;
    private long stopTime;

    enum Type {

        COMMAND("Command"),
        QUERY("Query");

        private String typeName;

        Type(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }

    public abstract Object getSource();

    public abstract String toJson();

    public Type getType() {

        if (getOrigin().contains(Type.COMMAND.toString())) return Type.COMMAND;
        if (getOrigin().contains(Type.QUERY.toString())) return Type.QUERY;
        return null;
    }

    public String getOrigin() {
        return getSource().getClass().getSimpleName();
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isDomainException() {
        return getException() instanceof DomainException;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        stopTime = System.nanoTime();
    }

    public long getElapsedTimeInNano() {
        return stopTime - startTime;
    }

    public long getElapsedTimeInMilli() {
        return getElapsedTimeInNano() / 1_000_000L;
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public boolean hasError() {
        return !isSuccess();
    }
}
