package site.backrer.decisiveBattle.actions;

public interface ContinuouslyExecutingEvents {
    int countTimeMax=60;
    boolean isTick();
    void event1();
}
