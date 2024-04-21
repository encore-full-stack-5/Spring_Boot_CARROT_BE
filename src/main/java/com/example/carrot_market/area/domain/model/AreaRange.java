package com.example.carrot_market.area.domain.model;

public enum AreaRange {
    Small(1), Medium(2), Large(3);

    private final int id;

    private AreaRange(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return switch (this) {
            case Small -> 1500;
            case Medium -> 3000;
            case Large -> 6000;
        };
    }
    public static AreaRange convertIDToAreaRange(int id) {
        return switch (id) {
            case 1 -> AreaRange.Small;
            case 2 -> AreaRange.Medium;
            case 3 -> AreaRange.Large;
            default -> throw new IllegalArgumentException("지역범위가 1 small 2 medium 3 large 범위 밖입니다." + id);
        };
    }
}
