package com.teamcautionrobotics.timed_testing;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public abstract class AbstractPIDSource implements PIDSource {
    protected PIDSourceType type;

    public AbstractPIDSource(PIDSourceType sourceType) {
        type = sourceType;
    }

    @Override
    public void setPIDSourceType(PIDSourceType sourceType) {
        type = sourceType;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return type;
    }

    @Override
    public double pidGet() {
        switch (type) {
        case kDisplacement:
            return getDisplacement();
        case kRate:
            return getRate();
        default:
            return 0.0;
        }
    }

    protected abstract double getDisplacement();

    protected abstract double getRate();
}
