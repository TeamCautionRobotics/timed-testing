package com.teamcautionrobotics.timed_testing;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDSourceFactory {
    protected final DoubleSupplier displacement, rate;

    public PIDSourceFactory(DoubleSupplier displacement, DoubleSupplier rate) {
        this.displacement = displacement;
        this.rate = rate;
    }

    public PIDSource makePIDSource(PIDSourceType type) {
        return new AbstractPIDSource(type) {
            @Override
            protected double getDisplacement() {
                return displacement.getAsDouble();
            }

            @Override
            protected double getRate() {
                return rate.getAsDouble();
            }
        };
    }
}
