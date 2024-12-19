package com.google.thirdparty.publicsuffix;

public enum PublicSuffixType {
    PRIVATE(':', ','),
    REGISTRY('!', '?');
    
    private final char innerNodeCode;
    private final char leafNodeCode;

    private PublicSuffixType(char c2, char c3) {
        this.innerNodeCode = c2;
        this.leafNodeCode = c3;
    }

    /* access modifiers changed from: package-private */
    public char getLeafNodeCode() {
        return this.leafNodeCode;
    }

    /* access modifiers changed from: package-private */
    public char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    static PublicSuffixType fromCode(char c2) {
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.getInnerNodeCode() == c2 || publicSuffixType.getLeafNodeCode() == c2) {
                return publicSuffixType;
            }
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("No enum corresponding to given code: ");
        sb.append(c2);
        throw new IllegalArgumentException(sb.toString());
    }
}
