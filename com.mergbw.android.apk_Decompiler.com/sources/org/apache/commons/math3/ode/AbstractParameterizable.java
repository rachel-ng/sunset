package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractParameterizable implements Parameterizable {
    private final Collection<String> parametersNames;

    protected AbstractParameterizable(String... strArr) {
        this.parametersNames = new ArrayList();
        for (String add : strArr) {
            this.parametersNames.add(add);
        }
    }

    protected AbstractParameterizable(Collection<String> collection) {
        ArrayList arrayList = new ArrayList();
        this.parametersNames = arrayList;
        arrayList.addAll(collection);
    }

    public Collection<String> getParametersNames() {
        return this.parametersNames;
    }

    public boolean isSupported(String str) {
        for (String equals : this.parametersNames) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void complainIfNotSupported(String str) throws UnknownParameterException {
        if (!isSupported(str)) {
            throw new UnknownParameterException(str);
        }
    }
}
