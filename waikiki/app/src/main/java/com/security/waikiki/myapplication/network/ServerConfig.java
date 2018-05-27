package com.security.waikiki.myapplication.network;

public class ServerConfig
{
	public static final String BASE_URL = "http://192.168.0.15:7777/";

	public static int CONNECTION_TIMEOUT = 5000;
	public static int READ_TIMEOUT = 5000;
	public static int WRITE_TIMEOUT = 5000;

	private final int mDefaultTimeOut = 5 * 1000;

	private static ServerConfig sServerConfig;



	/* -- 여기서는 Configration 값만 정의 하는것으로... -- *
	private ServerConfig(Builder builder)
	{
		CONNECTION_TIMEOUT = builder.CONNECTION_TIMEOUT == 0 ? mDefaultTimeOut : builder.CONNECTION_TIMEOUT;
		READ_TIMEOUT = builder.READ_TIMEOUT == 0 ? mDefaultTimeOut : builder.READ_TIMEOUT;
		WRITE_TIMEOUT = builder.WRITE_TIMEOUT == 0 ? mDefaultTimeOut : builder.WRITE_TIMEOUT;

		sServerConfig = this;
	}

	public static ServerConfig getInstance()
	{
		if (sServerConfig == null)
		{
			return sServerConfig = new Builder().build();
		}

		return sServerConfig;
	}

	public static ServerInterface getInterfaceInstance()
	{
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
			.client(sServerConfig.gethttpClient())
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		ServerInterface serverInterface = retrofit.create(ServerInterface.class);
		return serverInterface;
	}

	public static ServerInterface getDefaultInterfaceInstance()
	{
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
			.client(gethttpClient())
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		ServerInterface serverInterface = retrofit.create(ServerInterface.class);
		return serverInterface;
	}

	private static OkHttpClient gethttpClient()
	{
		// TimeOut 주는 방법
		// 1. OkHttpClient 객체준비
		// 2. OkHttpClient 객체를 Bulid하기전에 Timeout메소드 들에 값을 넣음
		//   readTimeout(원하는 시간 Int, Timeunit.원하는 시간단위) -> 읽어오는 시간 Timeout
		//   connectTimeout(원하는 시간 Int, Timeunit.원하는 시간단위) -> 연결하는 시간 Timeout
		//   writeTimeout(원하는 시간 Int, Timeunit.원하는 시간단위) -> 쓰는데 걸리는 시간 Timeout
		// 3. Retrofit 객체가 Build되기전에 clinet("Put this Method")에 OkhttpClinet 객체를 넣어준다.
		OkHttpClient httpClient = new OkHttpClient.Builder()
			.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
			.build();

		return httpClient;
	}

	public static Builder newBuilder()
	{
		return new Builder();
	}

	public static class Builder
	{

		private int CONNECTION_TIMEOUT;
		private int READ_TIMEOUT;
		private int WRITE_TIMEOUT;

		private Builder()
		{
		}

		public ServerConfig build()
		{
			return new ServerConfig(this);
		}

		//한번에 모든 TimeOut을 설정한다.
		public Builder setTimeout(int timeout)
		{
			CONNECTION_TIMEOUT = timeout;
			READ_TIMEOUT = timeout;
			WRITE_TIMEOUT = timeout;
			return this;
		}

		//각가의 TimeOut시간을 따로 설정한다.
		public Builder setTimeout(int connection_timeout, int read_timeout, int write_timeout)
		{
			CONNECTION_TIMEOUT = connection_timeout;
			READ_TIMEOUT = read_timeout;
			WRITE_TIMEOUT = write_timeout;
			return this;
		}

		public Builder setConnectionTimeout(int connectionTimeout)
		{
			CONNECTION_TIMEOUT = connectionTimeout;
			return this;
		}

		public Builder setReadTimeout(int readTimeout)
		{
			READ_TIMEOUT = readTimeout;
			return this;
		}

		public Builder setWriteTimeout(int writeTimeout)
		{
			WRITE_TIMEOUT = writeTimeout;
			return this;
		}

	}
	/* -- */

}
