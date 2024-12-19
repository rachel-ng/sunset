package top.defaults.colorpicker;

public interface ColorObservable {
    int getColor();

    void subscribe(ColorObserver colorObserver);

    void unsubscribe(ColorObserver colorObserver);
}
