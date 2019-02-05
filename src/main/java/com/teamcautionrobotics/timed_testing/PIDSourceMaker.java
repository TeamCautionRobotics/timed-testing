package com.teamcautionrobotics.timed_testing;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PIDSourceType;

// I do not know what to call this. I expect this to not be a factory, since this
// is an instance of PIDSource, instead of having a method that makes PIDSource.
public class PIDSourceMaker extends AbstractPIDSource {
    protected PIDSourceType type;

    protected final DoubleSupplier displacement, rate;

    public PIDSourceMaker(PIDSourceType sourceType, DoubleSupplier displacement, DoubleSupplier rate) {
        super(sourceType);

        this.displacement = displacement;
        this.rate = rate;
    }

    @Override
    protected double getRate() {
        return rate.getAsDouble();
    }

    @Override
    protected double getDisplacement() {
        return displacement.getAsDouble();
    }
}
