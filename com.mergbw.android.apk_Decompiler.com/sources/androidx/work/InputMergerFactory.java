package androidx.work;

public abstract class InputMergerFactory {
    public abstract InputMerger createInputMerger(String str);

    public final InputMerger createInputMergerWithDefaultFallback(String str) {
        InputMerger createInputMerger = createInputMerger(str);
        return createInputMerger == null ? InputMerger.fromClassName(str) : createInputMerger;
    }

    public static InputMergerFactory getDefaultInputMergerFactory() {
        return new InputMergerFactory() {
            public InputMerger createInputMerger(String str) {
                return null;
            }
        };
    }
}
