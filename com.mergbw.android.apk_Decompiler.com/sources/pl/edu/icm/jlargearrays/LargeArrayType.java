package pl.edu.icm.jlargearrays;

public enum LargeArrayType {
    LOGIC,
    BYTE {
        public boolean isIntegerNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }
    },
    UNSIGNED_BYTE {
        public boolean isIntegerNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }
    },
    SHORT {
        public boolean isIntegerNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }

        public long sizeOf() {
            return 2;
        }
    },
    INT {
        public boolean isIntegerNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }

        public long sizeOf() {
            return 4;
        }
    },
    LONG {
        public boolean isIntegerNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }

        public long sizeOf() {
            return 8;
        }
    },
    FLOAT {
        public boolean isNumericType() {
            return true;
        }

        public boolean isRealNumericType() {
            return true;
        }

        public long sizeOf() {
            return 4;
        }
    },
    DOUBLE {
        public boolean isNumericType() {
            return true;
        }

        public boolean isRealNumericType() {
            return true;
        }

        public long sizeOf() {
            return 8;
        }
    },
    COMPLEX_FLOAT {
        public boolean isComplexNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }

        public long sizeOf() {
            return 4;
        }
    },
    COMPLEX_DOUBLE {
        public boolean isComplexNumericType() {
            return true;
        }

        public boolean isNumericType() {
            return true;
        }

        public long sizeOf() {
            return 8;
        }
    },
    STRING,
    OBJECT;

    public boolean isComplexNumericType() {
        return false;
    }

    public boolean isIntegerNumericType() {
        return false;
    }

    public boolean isNumericType() {
        return false;
    }

    public boolean isRealNumericType() {
        return false;
    }

    public long sizeOf() {
        return 1;
    }
}
