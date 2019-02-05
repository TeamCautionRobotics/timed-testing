package com.teamcautionrobotics.timed_testing;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveBase {

    private final SpeedController driveLeft;
    private final SpeedController driveRight;

    private final Encoder encoder;

    // You know it's a good API when we just make a field public, because we (I)
    // could not be bothered to wrap it
    public final PIDController pidController;

    public final PIDSourceFactory pidSourceFactory;

    public DriveBase(SpeedController left, SpeedController right, int encoderA, int encoderB) {
        driveLeft = left;
        driveRight = right;

        encoder = new Encoder(encoderA, encoderB, false, EncodingType.k4X);

        encoder.setDistancePerPulse((4 * Math.PI) / 1024);

        pidSourceFactory = new PIDSourceFactory(this::getDistance, this::getSpeed);

        pidController = new PIDController(0.04, 0, 0.1, 0, new DriveBasePIDSource(PIDSourceType.kDisplacement),
                this::drive);
        pidController.setOutputRange(-1, 1);
        pidController.setAbsoluteTolerance(3);
    }

    public void drive(double left, double right) {
        driveLeft.set(left);
        driveRight.set(-right);
    }

    public void drive(double speed) {
        drive(speed, speed);
    }

    public void resetEncoder() {
        encoder.reset();
    }

    public double getDistance() {
        return encoder.getDistance();
    }

    public double getSpeed() {
        return encoder.getRate();
    }

    public PIDSource makePidSource(PIDSourceType type) {
        return new PIDSourceMaker(type, this::getDistance, this::getSpeed);
    }

    public class DriveBasePIDSource extends AbstractPIDSource {
        public DriveBasePIDSource(PIDSourceType sourceType) {
            super(sourceType);
        }

        @Override
        protected double getDisplacement() {
            return getDistance();
        }

        @Override
        protected double getRate() {
            return getSpeed();
        }
    }
}