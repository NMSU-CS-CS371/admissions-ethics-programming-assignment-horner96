// Admissions.java
// Contains the scoring models (Blind vs Aware).

public class Admissions {

    // Blind model (only academic/performance factors)
    public static double blindScore(Applicant app) {
        double score = 0.0;
        score += (app.gpa / 4.0) * 0.3;     // GPA lowered to 30%
        score += (app.test / 1600.0) * 0.3;  // Test kept at 30%
        score += app.extra * 0.1;          // Extracurriculars kept at 10%
        score += app.essay * 0.1;       // Essay kept at 10%
        score += app.rec * 0.2;          // Recommendations increased to 20%
        return score; // final score between 0 and 1
    }

    // Aware model (adds equity and context)
    public static double awareScore(Applicant app) {
        double score = blindScore(app);

        if (app.income < 40000) score += 0.05;     // low-income increased to 5%
        else if (app.income < 80000) score += 0.00; // moderate-income lowered to 0%
        if (app.firstGen) score += 0.03;           // first-generation bonus increased to 5%
        if (app.disability) score += 0.05;         // accessibility consideration increased to 5%
        if (app.legacy) score += 0.02;             // legacy advantage increased to 2%
        if (app.local) score += 0.03;              // local preference increased to 5%
             
        return Math.min(score, 1.0);               // cap score at 1.0
    }
}