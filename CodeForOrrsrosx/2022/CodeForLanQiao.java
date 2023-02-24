package com.simblockchain.Utils;

import com.simblockchain.entity.WholeTransaction;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * @ClassName: ECCUtil
 * @Description : 公钥私钥配置类
 * @Author : orrsrosx
 * @Date : 2022/2/10
 */
public class CodeForLanQiao {

    private final static long Q3 = 10;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long d1 = in.nextLong();
        float p1 = in.nextFloat();
        float q1 = in.nextFloat();

        long d2 = in.nextLong();
        float p2 = in.nextFloat();
        float q2 = in.nextFloat();

        float[] a1 = run(0f, 0f, d1, p1);
        float[] a2 = run(a1[0], a1[1], d1 + 2, q1);

        float[] b1 = run(0f, 0f, d2, p2);
        float[] b2 = run(b1[0], b1[1], d2 + 2, q2);
//        System.out.println(Arrays.toString(a2));
//        System.out.println(Arrays.toString(b2));
        long ans = (long) Math.abs(a2[0] - b2[0]);
        ans += (long) Math.abs((a2[1] - b2[1]) / Q3);
        System.out.println(ans);
    }

    public static float[] run(float x, float y, long d, float p) {
        d = d % 6;
        if (d == 0) {
            x += -p;
        } else if (d == 1) {
            float t = p / 2;
            x += -t;
            y += t * Q3;
        } else if (d == 5) {
            float t = p / 2;
            x += -t;
            y += -t * Q3;
        } else if (d == 3) {
            x += p;
        } else if (d == 2) {
            float t = p / 2;
            x += t;
            y += t * Q3;
        } else if (d == 4) {
            float t = p / 2;
            x += t;
            y += -t * Q3;
        }
        return new float[]{x, y};
    }


}
