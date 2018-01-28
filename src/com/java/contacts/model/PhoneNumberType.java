package com.java.contacts.model;

/**
 * Clasa de construire a obiectelor de tip Phone
 * <p>Verificare tipului de numar intors si intoarcerea
 * unui obiect corespunzator de tip <code>MobilePhone</code> sau de tip <code>LandPhone</code></p>
 *
 * @author Liviu Jianu
 */

public class PhoneNumberType {

    private static final int LUNGIME_NUMAR = 10;
    private static final String identificareNumarFix = "02";
    private static final String identificareNumarMobil = "07";

    /**
     * @param numarTel sir de caractere care reprezinta numarul de telefon
     *                 in urma caruia se va construi un obiect de tip LandPhone sau MobilePhone
     * @return Obiect tip numarTelefon in functie de formatul numarului
     * throws Exception exceptie in cazul in care numarul de telefon
     *                   introdus este incorect.
     */
    public static Phone getPhoneNumberType(String numarTel) throws RuntimeException {
        if ((numarTel == null) || (numarTel.length() != LUNGIME_NUMAR)) {
            throw new RuntimeException("Formatul numarului introdus este incorect!");
        }

        if (numarTel.startsWith(identificareNumarMobil)) {
            return new MobilePhone(numarTel);
        } else if (numarTel.startsWith(identificareNumarFix)) {
            return new LandPhone(numarTel);
        } else {
            throw new RuntimeException("Formatul numarului introdus este incorect!");
        }
    }
}