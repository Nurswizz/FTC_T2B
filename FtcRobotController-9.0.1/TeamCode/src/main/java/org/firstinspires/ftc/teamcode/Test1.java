package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.checkerframework.checker.units.qual.C;

@TeleOp
public class Test1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Servo intake = hardwareMap.get(Servo.class, "rotator");

        waitForStart();



        while(opModeIsActive()) {
           intake.setPosition(Constants.encoder1);
        }




    }
}