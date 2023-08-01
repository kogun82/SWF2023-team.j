package org.team.j.geno.block.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;

public class Utils {

	protected static Utils instance = null;

	private Gson gson = new Gson();

	public static final Utils getInstance() {

		if (instance == null) {
			instance = new Utils();
		} else {
			return instance;
		}

		return instance;
	}

	public String doGet(String url) {

		StringBuffer buffer = new StringBuffer();

		try {
			URL _url = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(600000);
			connection.setConnectTimeout(100000);

			try (InputStream in = connection.getInputStream();
					ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				byte[] buf = new byte[1024 * 8];
				int length = 0;
				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				buffer.append(new String(out.toByteArray(), "UTF-8"));
			}

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString().trim();
	}

	public Object doGet(String url, Type type) {
		return gson.fromJson(doGet(url), type);
	}

	private void disableSSLVerification() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String doPost(String url, String body) {

		disableSSLVerification();

		StringBuffer buffer = new StringBuffer(); // get body

		try {

			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoOutput(true);
			connection.setReadTimeout(600000);
			connection.setConnectTimeout(100000);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			try (OutputStream out = connection.getOutputStream()) {
				out.write(body.getBytes());
				out.flush();
			}

			try (InputStream in = connection.getInputStream();
					ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				byte[] buf = new byte[1024 * 8];
				int length = 0;
				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				buffer.append(new String(out.toByteArray(), "UTF-8"));
			}

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString().trim();
	}

	public Object doPost(String url, String body, Type type) {
		return gson.fromJson(doPost(url, body), type);
	}

	public String doPut(String url, String jsonData) {

		StringBuffer buffer = new StringBuffer();

		try {
			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setReadTimeout(600000);
			connection.setConnectTimeout(100000);

			if (jsonData != null) {
				OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
				osw.write(jsonData);
				osw.flush();
				osw.close();
			}

			try (InputStream in = connection.getInputStream();
					ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				byte[] buf = new byte[1024 * 8];
				int length = 0;
				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				buffer.append(new String(out.toByteArray(), "UTF-8"));
			}

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString().trim();
	}

	public Object doPut(String url, String json_data, Type type) {
		return gson.fromJson(doPut(url, json_data), type);
	}

	public int doDelete(String url) {
		int res = -1;

		try {
			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();

			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("charset", "utf-8");
			connection.setUseCaches(false);
			connection.setReadTimeout(600000);
			connection.setConnectTimeout(100000);

			res = Integer.parseInt(connection.getHeaderField("custom_status_code"));

			// log(connection);
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public String getCurruntTime() {

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

		return format.format(now);
	}

	public String getNewHash(String geneNo) {

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] digest = md.digest(geneNo.getBytes(StandardCharsets.UTF_8));
		String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();

		return sha256;
	}

	public String getNewUID() {
		Random rand = new Random();
		String geneNo = "GENE" + rand.nextInt(1000000);
		return geneNo;
	}
}
