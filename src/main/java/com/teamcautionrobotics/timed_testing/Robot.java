/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.teamcautionrobotics.timed_testing;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Joystick joystickLeft, joystickRight;

  private DriveBase driveBase;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code, because why use constructors?
   */
  @Override
  public void robotInit() {
    joystickLeft = new Joystick(0);
    joystickRight = new Joystick(1);

    driveBase = new DriveBase(new VictorSP(0), new VictorSP(1), 0, 1);

    // Let us pretend we need some PIDSources from the driveBase to make our code
    // extra saurcy
    PIDSource oldWay, factoryWay, otherWay;

    // Use the driveBase's inner class to make the PIDSource
    oldWay = driveBase.new DriveBasePIDSource(PIDSourceType.kDisplacement);

    // Use the PIDSource factory instantiated by and a field of the driveBase to
    // make the PIDSource
    factoryWay = driveBase.pidSourceFactory.makePIDSource(PIDSourceType.kDisplacement);

    // Call a method of driveBase that behavies like a factory
    otherWay = driveBase.makePidSource(PIDSourceType.kDisplacement);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // Invert to correct for joystick's inversion of Y axis
    double forwardCommand = -joystickRight.getY();
    double turnCommand = joystickLeft.getX();

    double leftPower = forwardCommand + turnCommand;
    double rightPower = forwardCommand - turnCommand;

    driveBase.drive(leftPower, rightPower);
  }
}
