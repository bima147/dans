package com.base.dans.util;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 13/05/24 19.17
@Last Modified 13/05/24 19.17
Version 1.0
 */

public class ConstantMessage {
    /*
    Memperbolehkan nilai numerik dari 0 hingga 9.
    Memperbolehkan Huruf besar dan huruf kecil dari a sampai z.
    Yang diperbolehkan hanya garis bawah “_”, tanda hubung “-“ dan titik “.”
    Titik tidak diperbolehkan di awal dan akhir local part (sebelum tanda @).
    Titik berurutan tidak diperbolehkan.
    Local part, maksimal 64 karakter.
     */
//    public final static String REGEX_EMAIL_STRICT = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

    public final static String USERNAME_NULL = "Username tidak boleh null";
    public final static String USERNAME_EMPTY = "Username tidak boleh null";
    public final static String USERNAME_BLANK = "Username tidak boleh blank";

    public final static String PASSWORD_NULL = "Password tidak boleh null";
    public final static String PASSWORD_EMPTY = "Password tidak boleh null";
    public final static String PASSWORD_BLANK = "Password tidak boleh blank";

    public final static String IP_LOGIN_NULL = "IP Login tidak boleh null";

    public final static String CONTENT_TYPE_CSV = "text/csv";
    public final static String ERROR_NOT_CSV_FILE = "FORMAT FILE HARUS CSV ";

    public final static String ERROR_UPLOAD_CSV = "UPLOAD FILE GAGAL ";
    /*REGEX*/

    public final static String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    /*
     * Tidak memperbolehkan tanda | (pipa) dan ' (petik 1)
     */
    public final static String REGEX_EMAIL_STANDARD_RFC5322  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String REGEX_DATE_YYYYMMDD  = "^([0-9][0-9])?[0-9][0-9]-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";
    public final static String REGEX_DATE_DDMMYYYY  = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";

    /*Global*/
    public final static String SUCCESS_SAVE = "DATA BERHASIL DIBUAT";
    public final static String SUCCESS_FIND_BY = "OK";
    public final static String WARNING_NOT_FOUND = "DATA TIDAK DITEMUKAN";
    public final static String WARNING_DATA_EMPTY = "DATA TIDAK ADA";

    public final static String ERROR_DATA_INVALID = "DATA TIDAK VALID";
    public final static String ERROR_INTERNAL_SERVER = "INTERNAL SERVER ERROR";
    public final static String ERROR_MAIL_FORM_JSON = "Malformed JSON request";
    public final static String ERROR_EMAIL_FORMAT_INVALID = "FORMAT EMAIL TIDAK SESUAI (Nomor/Karakter@Nomor/Karakter)";
    public final static String ERROR_PHONE_NUMBER_FORMAT_INVALID = "FORMAT NOMOR HANDPHONE TIDAK SESUAI (+628XX-)";
    public final static String ERROR_DATE_FORMAT_YYYYMMDD = "FORMAT TANGGAL TIDAK SESUAI (YYYY-mm-dd)";

    public final static String ERROR_UNEXPECTED = "UNEXPECTED ERROR";
    public final static String ERROR_UNPROCCESSABLE = "Validation error. Check 'errors' field for details.";

    public final static String ERROR_NO_CONTENT = "PERMINTAAN TIDAK DAPAT DIPROSES";
    public final static String WELCOME_MESSAGE = "This is Springboot BootCamp BCAF BATCH 1";
    public final static String TAKE_TOUR = "Ready To Start";

    /*Customer*/
    public final static String SUCCESS = "";
    public final static String ERROR = "";
    public final static String WARNING_EMAIL_EXIST = "EMAIL SUDAH TERDAFTAR";
    public final static String WARNING_CUSTOMER_NOT_FOUND = "CUSTOMER BELUM TERDAFTAR";

    /*Products*/
    public final static String WARNING_PRODUCT_NOT_FOUND = "PRODUK TIDAK DITEMUKAN";
    public final static String WARNING_PROD_NAME_MANDATORY = "NAMA PRODUK WAJIB DIISI";
    public final static String WARNING_PROD_DESC_MANDATORY = "DESKRIPSI PRODUK WAJIB DIISI";
    public final static String WARNING_PROD_PRICE_MANDATORY = "HARGA PRODUK WAJIB DIISI";
    public final static String WARNING_PRODUCT_PRICE_SOP = "HARGA TIDAK BOLEH 1/2 ATAU 3 KALI DARI HARGA SEBELUMNYA";

    /*Reseller*/
    public final static String WARNING_RESELLER_NAME_EXIST = "NAMA RESELLER SUDAH TERDAFTAR";
    public final static String WARNING_RESELLER_NOT_FOUND = "RESELLER BELUM TERDAFTAR";

    public final static String WARNING_NUMBER_OF_EMPLOYEES = "JUMLAH KARYAWAN TIDAK BOLEH KURANG DARI 1";
    public final static String WARNING_EMAIL_REQUIRED = "EMAIL HARUS DIISI";
    public final static String WARNING_RESELLER_NAME_REQUIRED = "RESELER NAME HARUS DIISI";



    public final static String WARNING_NUMBER_OF_EMPLOYEES_REQUIRED = "JUMLAH KARYAWAN HARUS DIISI";

    /*Business Type*/
    public final static String WARNING_BUSINESS_NAME_REQUIRED = "NAMA BISNIS HARUS TERISI";
    public final static String WARNING_BUSINESS_TYPE_NOT_FOUND = "TIPE BISNIS BELUM TERDAFTAR";

    /*Expedition*/
    public final static String WARNING_EXPEDITION_NAME_REQUIRED = "NAMA EXPEDISI HARUS TERISI";
    public final static String WARNING_EKSPEDITION_NOT_FOUND = "EKSPEDISI BELUM TERDAFTAR";


    /*Division*/
    public final static String WARNING_DIVISION_NAME_MANDATORY = "NAMA DIVISI HARUS TERISI";
    public final static String WARNING_DIVISION_NAME_LENGTH = "PANJANG NAMA DIVISI TIDAK SESUAI";
    public final static String WARNING_DIVISION_DESC_MANDATORY = "DISKRIPSI DIVISI HARUS TERISI";

    /*Employee*/
    public final static String WARNING_EMPL_EMAIL_MANDATORY = "EMAIL EMPLOYEE HARUS TERISI";
    public final static String WARNING_EMPL_MAX_LENGTH_EMAIL = "PANJANG EMAIL EMPLOYEE TIDAK SESUAI";
    public final static String WARNING_EMPL_MAX_LENGTH_PHONE = "PANJANG NO TELEPON EMPLOYEE TIDAK SESUAI";

    public final static String WARNING_NAME_REQUIRED = "NAMA HARUS TERISI";

    public final static String WARNING_ADDRESS_REQUIRED = "ALAMAT HARUS TERISI";
    public final static String WARNING_PHONE_REQUIRED = "NO TELP HARUS TERISI";

    public final static String WARNING_NAME_MAX_LENGTH = "PANJANG NAMA TIDAK SESUAI";

    public final static String WARNING_PHONE_MAX_LENGTH = "PANJANG NO TELEPON TIDAK SESUAI";


    public final static String WARNING_EMAIL_MAX_LENGTH = "PANJANG EMAIL TIDAK SESUAI";


    public final static String WARNING_BUSINESS_DESCRIPTION_REQUIRED = "DIKRIPSI BISNIS HARUS TERISI";

    public final static String WARNING_NAME_EXIST = "NAMA SUDAH TERDAFTAR";

    public final static String SUCCESS_SEND_EMAIL = "SILAHKAN CEK EMAIL YANG TELAH ANDA DAFTARKAN";


    public final static String WARNING_BUSINESS_CATEGORY_MAX_LENGTH = "PANJANG CATEGORY BISNIS TIDAK SESUAI";


    public final static String WARNING_BUSINESS_CATEGORY_REQUIRED = "KATEGORI BISNIS HARUS TERISII";


    public final static String WARNING_BIRTHDATE_REQUIRED = "TANGGAL LAHIR HARUS TERISI";

    public final static String WARNING_KODEPOS_REQUIRED = "KODEPOS HARUS TERISI";

    public final static String WARNING_OTENTIKASI_FAIL = "OTENTIKASI GAGAL";

    public final static String SUCCESS_OTENTIKASI = "EMAIL VALID";

    public final static String WARNING_KODEPOS_NOT_START_0 = "KODEPOS TIDAK BOLEH DIMULAI DARI NOL";

    /*Nasabah*/


    public final static String WARNING_REKENING_REQUIRED = "REKENING HARUS TERISI";

    public final static String WARNING_SALDO_REQUIRED = "SALDO HARUS TERISI";


    public final static String WARNING_SALDO_LIMIT = "TRANFER GAGAL !! SALDO TIDAK MEMENUHI";

    public final static String WARNING_REKENING_ASAL_NOT_FOUND = "REKENING ASAL TIDAK TERDAFTAR";

    public final static String WARNING_REKENING_PENERIMA_NOT_FOUND = "REKENING PENERIMA TIDAK TERDAFTAR";
    public final static String WARNING_REKENING_ASAL_REQUIRED = "REKENING ASAL HARUS TERISI";

    public final static String WARNING_REKENING_PENERIMA_REQUIRED = "REKENING PENERIMA HARUS TERISI";
    public final static String WARNING_NOMINAL_REQUIRED = "NOMINAL TRANSFER HARUS TERISI";

    public final static String SUCCESS_TRANSFER_DANA = "DANA BERHASIL DI TRANSAFER";

    public final static String WARNING_FAIL_TRANSFER = "PROSES TRANSFER GAGAL";

    /*USER*/

    public final static String WARNING_BAD_CRIDENTIAL = "USERNAME ATAU PASSWORD SALAH";


    public final static String WARNING_MAX_LENGTH = "PANJANG TIDAK SESUAI";

    public final static String WARNING_DATA_NOT_FOUND = "DATA TIDAK DITEMUKAN";


    public final static String SUCCESS_DELETE = "DATA BERHASIL DIHAPUS";
}
