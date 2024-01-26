package org.firstinspires.ftc.teamcode;

import android.graphics.drawable.Drawable;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class T2b extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBackRight = hardwareMap.get(DcMotor.class, "motorBackRight");
        DcMotor arm1 = hardwareMap.get(DcMotor.class, "arm1");
        DcMotor arm2 = hardwareMap.get(DcMotor.class, "arm2");
        Servo intake = hardwareMap.get(Servo.class, "intake");
        Servo rotator  = hardwareMap.get(Servo.class, "rotator");


        arm1.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);

//         Reverse the right side motors
//         Reverse left motors if you are using NeveRests

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI/4;
            double rightX = gamepad1.right_stick_x * 0.5;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;
            motorFrontLeft.setPower(v1 * 1.3);
            motorFrontRight.setPower(v2 * 1.3);
            motorBackLeft.setPower(v3 * 1.3);
            motorBackRight.setPower(v4 * 1.3);
            if (gamepad2.dpad_right)
            {
                rotator.setPosition(0.2);
            }
            else if (gamepad2.dpad_left)
            {
                rotator.setPosition(0.9);
            }

            if (gamepad2.left_bumper)
            {
                intake.setPosition(0.37);
            }
            else if (gamepad2.right_bumper)
            {
                intake.setPosition(0.55);
            }
            if (gamepad2.a)
            {
                arm1.setPower(0.6);
                arm2.setPower(0.6);
            }
            else if (gamepad2.b)
            {
                arm1.setPower(-0.6);
                arm2.setPower(-0.6);
            }
            else
            {
                arm1.setPower(0);
                arm2.setPower(0);
                arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }



        }

    }
}