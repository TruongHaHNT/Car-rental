USE [master]
GO
/****** Object:  Database [CarRental]    Script Date: 3/19/2020 5:47:38 AM ******/
CREATE DATABASE [CarRental]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CarRental', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS2014\MSSQL\DATA\CarRental.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CarRental_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS2014\MSSQL\DATA\CarRental_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CarRental] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CarRental].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CarRental] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CarRental] SET ARITHABORT OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CarRental] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CarRental] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CarRental] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CarRental] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CarRental] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CarRental] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CarRental] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CarRental] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CarRental] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CarRental] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CarRental] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CarRental] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CarRental] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CarRental] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CarRental] SET  MULTI_USER 
GO
ALTER DATABASE [CarRental] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CarRental] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CarRental] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CarRental] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [CarRental] SET DELAYED_DURABILITY = DISABLED 
GO
USE [CarRental]
GO
/****** Object:  Table [dbo].[tbl_Booking_Bill]    Script Date: 3/19/2020 5:47:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Booking_Bill](
	[bill_ID] [int] IDENTITY(1,1) NOT NULL,
	[bill_u_email] [varchar](254) NULL,
	[bill_booking_Date] [datetime] NULL,
	[bill_discountID] [int] NULL,
	[bill_statusID] [int] NULL,
 CONSTRAINT [PK_tbl_Booking_Bill] PRIMARY KEY CLUSTERED 
(
	[bill_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Booking_Bill_Details]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Booking_Bill_Details](
	[dt_bill_ID] [int] NULL,
	[dt_carID] [int] NULL,
	[dt_dateRent] [datetime] NULL,
	[dt_dateReturn] [datetime] NULL,
	[dt_amount] [int] NULL,
	[dt_ID] [int] IDENTITY(1,1) NOT NULL,
	[dt_statusID] [int] NULL,
	[dt_price] [float] NULL,
	[dt_updateDate] [datetime] NULL,
 CONSTRAINT [PK_tbl_Booking_Bill_Details] PRIMARY KEY CLUSTERED 
(
	[dt_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_Booking_Details_Status]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Booking_Details_Status](
	[bk_dt_st_ID] [int] IDENTITY(1,1) NOT NULL,
	[bk_dt_st_status] [varchar](10) NULL,
 CONSTRAINT [PK_tbl_Booking_Details_Status] PRIMARY KEY CLUSTERED 
(
	[bk_dt_st_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Booking_Status]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Booking_Status](
	[bk_st_ID] [int] IDENTITY(1,1) NOT NULL,
	[bk_st_status] [varchar](10) NULL,
 CONSTRAINT [PK_RentStatus] PRIMARY KEY CLUSTERED 
(
	[bk_st_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Car]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Car](
	[c_ID] [int] IDENTITY(1,1) NOT NULL,
	[c_name] [varchar](50) NULL,
	[c_amount] [int] NULL,
	[c_image] [text] NULL,
	[c_typeID] [int] NULL,
	[c_brandID] [int] NULL,
	[c_statusID] [int] NULL,
	[c_dateRent] [datetime] NULL,
	[c_dateReturn] [datetime] NULL,
	[c_pricePerDay] [float] NULL,
	[c_colorID] [int] NULL,
	[c_updateDate] [datetime] NULL,
 CONSTRAINT [PK_Car] PRIMARY KEY CLUSTERED 
(
	[c_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_CarBrand]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_CarBrand](
	[c_br_ID] [int] IDENTITY(1,1) NOT NULL,
	[c_br_name] [varchar](30) NULL,
 CONSTRAINT [PK_CarBrand] PRIMARY KEY CLUSTERED 
(
	[c_br_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_CarColor]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_CarColor](
	[c_col_ID] [int] IDENTITY(1,1) NOT NULL,
	[c_col_name] [varchar](30) NULL,
 CONSTRAINT [PK_CarColor] PRIMARY KEY CLUSTERED 
(
	[c_col_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_CarStatus]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_CarStatus](
	[c_st_ID] [int] IDENTITY(1,1) NOT NULL,
	[c_st_name] [varchar](10) NULL,
 CONSTRAINT [PK_CarStatus] PRIMARY KEY CLUSTERED 
(
	[c_st_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_CarType]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_CarType](
	[c_ty_ID] [int] IDENTITY(1,1) NOT NULL,
	[c_ty_name] [varchar](30) NULL,
 CONSTRAINT [PK_CarType] PRIMARY KEY CLUSTERED 
(
	[c_ty_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Discount]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Discount](
	[dsc_ID] [int] IDENTITY(0,1) NOT NULL,
	[dsc_code] [varchar](20) NULL,
	[dsc_exDate] [datetime] NULL,
	[dsc_value] [int] NULL,
	[dsc_statusID] [int] NULL,
 CONSTRAINT [PK_tbl_Discount] PRIMARY KEY CLUSTERED 
(
	[dsc_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Discount_Status]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Discount_Status](
	[dsc_st_ID] [int] NOT NULL,
	[dsc_st_status] [varchar](10) NULL,
 CONSTRAINT [PK_tbl_Discount_Status] PRIMARY KEY CLUSTERED 
(
	[dsc_st_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_ImageUI]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_ImageUI](
	[ui_name] [varchar](30) NOT NULL,
	[ui_image] [text] NULL,
 CONSTRAINT [PK_imageUI] PRIMARY KEY CLUSTERED 
(
	[ui_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Rating]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Rating](
	[rt_ID] [int] IDENTITY(1,1) NOT NULL,
	[rt_u_email] [varchar](254) NULL,
	[rt_CarID] [int] NULL,
	[rt_Value] [int] NULL,
 CONSTRAINT [PK_tbl_Rating] PRIMARY KEY CLUSTERED 
(
	[rt_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_UserInfo]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_UserInfo](
	[u_email] [varchar](254) NOT NULL,
	[u_name] [varchar](30) NULL,
	[u_password] [varchar](70) NULL,
	[u_roleID] [varchar](3) NULL,
	[u_statusID] [varchar](3) NULL,
	[u_phone] [varchar](10) NULL,
	[u_address] [text] NULL,
	[u_createDate] [datetime] NULL,
 CONSTRAINT [PK_UserInfo] PRIMARY KEY CLUSTERED 
(
	[u_email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_UserRole]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_UserRole](
	[u_rl_roleID] [varchar](3) NOT NULL,
	[u_rl_role] [varchar](10) NULL,
 CONSTRAINT [PK_UserRole] PRIMARY KEY CLUSTERED 
(
	[u_rl_roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_UserStatus]    Script Date: 3/19/2020 5:47:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_UserStatus](
	[u_st_statusID] [varchar](3) NOT NULL,
	[u_st_status] [varchar](10) NULL,
 CONSTRAINT [PK_UserStatus] PRIMARY KEY CLUSTERED 
(
	[u_st_statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_Booking_Bill] ON 

INSERT [dbo].[tbl_Booking_Bill] ([bill_ID], [bill_u_email], [bill_booking_Date], [bill_discountID], [bill_statusID]) VALUES (1, N'usera@gmail.com', CAST(N'2020-03-14 13:03:41.193' AS DateTime), 2, 1)
INSERT [dbo].[tbl_Booking_Bill] ([bill_ID], [bill_u_email], [bill_booking_Date], [bill_discountID], [bill_statusID]) VALUES (2, N'truonghanguyen1999@gmail.com', CAST(N'2020-03-14 13:07:51.930' AS DateTime), 0, 1)
INSERT [dbo].[tbl_Booking_Bill] ([bill_ID], [bill_u_email], [bill_booking_Date], [bill_discountID], [bill_statusID]) VALUES (3, N'truonghanguyen1999@gmail.com', CAST(N'2020-03-15 09:02:47.837' AS DateTime), 0, 2)
INSERT [dbo].[tbl_Booking_Bill] ([bill_ID], [bill_u_email], [bill_booking_Date], [bill_discountID], [bill_statusID]) VALUES (4, N'usera@gmail.com', CAST(N'2020-03-18 10:10:29.600' AS DateTime), 4, 2)
SET IDENTITY_INSERT [dbo].[tbl_Booking_Bill] OFF
SET IDENTITY_INSERT [dbo].[tbl_Booking_Bill_Details] ON 

INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (1, 3, CAST(N'2020-03-15 07:00:00.000' AS DateTime), CAST(N'2020-03-15 22:00:00.000' AS DateTime), 2, 1, 1, 375, CAST(N'2020-03-14 13:03:41.467' AS DateTime))
INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (1, 1, CAST(N'2020-03-15 07:00:00.000' AS DateTime), CAST(N'2020-03-15 22:00:00.000' AS DateTime), 11, 2, 1, 312.5, CAST(N'2020-03-14 13:03:41.467' AS DateTime))
INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (2, 5, CAST(N'2020-03-15 07:00:00.000' AS DateTime), CAST(N'2020-03-15 22:00:00.000' AS DateTime), 1, 3, 1, 250, CAST(N'2020-03-14 13:07:52.027' AS DateTime))
INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (2, 1, CAST(N'2020-03-15 07:00:00.000' AS DateTime), CAST(N'2020-03-15 22:00:00.000' AS DateTime), 20, 4, 1, 312.5, CAST(N'2020-03-14 13:07:52.027' AS DateTime))
INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (3, 3, CAST(N'2020-03-15 07:00:00.000' AS DateTime), CAST(N'2020-03-15 22:00:00.000' AS DateTime), 1, 5, 2, 375, CAST(N'2020-03-15 09:02:25.933' AS DateTime))
INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (3, 11, CAST(N'2020-03-15 07:00:00.000' AS DateTime), CAST(N'2020-03-15 22:00:00.000' AS DateTime), 1, 6, 2, 312.5, CAST(N'2020-03-15 09:02:47.837' AS DateTime))
INSERT [dbo].[tbl_Booking_Bill_Details] ([dt_bill_ID], [dt_carID], [dt_dateRent], [dt_dateReturn], [dt_amount], [dt_ID], [dt_statusID], [dt_price], [dt_updateDate]) VALUES (4, 9, CAST(N'2020-03-19 07:00:00.000' AS DateTime), CAST(N'2020-03-19 22:00:00.000' AS DateTime), 300, 7, 2, 281.25, CAST(N'2020-03-18 10:10:29.600' AS DateTime))
SET IDENTITY_INSERT [dbo].[tbl_Booking_Bill_Details] OFF
SET IDENTITY_INSERT [dbo].[tbl_Booking_Details_Status] ON 

INSERT [dbo].[tbl_Booking_Details_Status] ([bk_dt_st_ID], [bk_dt_st_status]) VALUES (1, N'Active')
INSERT [dbo].[tbl_Booking_Details_Status] ([bk_dt_st_ID], [bk_dt_st_status]) VALUES (2, N'InActive')
SET IDENTITY_INSERT [dbo].[tbl_Booking_Details_Status] OFF
SET IDENTITY_INSERT [dbo].[tbl_Booking_Status] ON 

INSERT [dbo].[tbl_Booking_Status] ([bk_st_ID], [bk_st_status]) VALUES (1, N'Active')
INSERT [dbo].[tbl_Booking_Status] ([bk_st_ID], [bk_st_status]) VALUES (2, N'InActive')
SET IDENTITY_INSERT [dbo].[tbl_Booking_Status] OFF
SET IDENTITY_INSERT [dbo].[tbl_Car] ON 

INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (1, N'2020 Toyota RAV4', 300, N'2020ToyotaRAV4.png', 2, 1, 1, CAST(N'2020-01-20 00:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 500, 4, CAST(N'2020-01-01 07:10:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (2, N'Prius Business Edition', 400, N'PriusBusinessEdition.png', 1, 1, 1, CAST(N'2020-01-20 00:00:00.000' AS DateTime), CAST(N'2020-06-28 22:00:00.000' AS DateTime), 550, 1, CAST(N'2020-01-01 07:01:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (3, N'2020 Audi RS3', 200, N'2020AudiRS3.jpg', 1, 4, 1, CAST(N'2020-02-01 07:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 600, 1, CAST(N'2020-01-01 08:00:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (4, N'2020 Honda CR-V', 200, N'2020HondaCRV.jpg', 2, 5, 1, CAST(N'2020-01-21 00:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 400, 4, CAST(N'2020-01-01 07:12:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (5, N'BMW 2 Series Bratwurst Van Rendered', 300, N'BMW2SeriesBratwurstVanRendered.jpg', 3, 2, 1, CAST(N'2020-01-30 04:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 400, 4, CAST(N'2020-01-01 07:30:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (6, N'Volvo Opel', 400, N'VolvoOpel.jpg', 3, 3, 1, CAST(N'2020-02-01 00:00:00.000' AS DateTime), CAST(N'2020-06-28 22:00:00.000' AS DateTime), 500, 4, CAST(N'2020-01-01 07:00:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (8, N'Volvo XC90', 400, N'VolvoXC90.jpg', 2, 3, 1, CAST(N'2020-02-01 00:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 400, 5, CAST(N'2020-01-01 07:00:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (9, N'Volvo xc40', 300, N'volvoxc40.jpg', 2, 3, 1, CAST(N'2020-02-01 00:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 450, 1, CAST(N'2020-01-01 07:00:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (10, N'Audi q3', 500, N'audiq3.jpg', 2, 4, 1, CAST(N'2020-02-03 00:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 500, 5, CAST(N'2020-01-01 07:00:00.000' AS DateTime))
INSERT [dbo].[tbl_Car] ([c_ID], [c_name], [c_amount], [c_image], [c_typeID], [c_brandID], [c_statusID], [c_dateRent], [c_dateReturn], [c_pricePerDay], [c_colorID], [c_updateDate]) VALUES (11, N'2020 BMW x1 euro', 200, N'2020bmwx1euro.jpg', 2, 2, 1, CAST(N'2020-03-02 00:00:00.000' AS DateTime), CAST(N'2020-06-30 22:00:00.000' AS DateTime), 500, 5, CAST(N'2020-01-01 07:07:20.200' AS DateTime))
SET IDENTITY_INSERT [dbo].[tbl_Car] OFF
SET IDENTITY_INSERT [dbo].[tbl_CarBrand] ON 

INSERT [dbo].[tbl_CarBrand] ([c_br_ID], [c_br_name]) VALUES (1, N'TOYOTA')
INSERT [dbo].[tbl_CarBrand] ([c_br_ID], [c_br_name]) VALUES (2, N'BMW')
INSERT [dbo].[tbl_CarBrand] ([c_br_ID], [c_br_name]) VALUES (3, N'VOLVO')
INSERT [dbo].[tbl_CarBrand] ([c_br_ID], [c_br_name]) VALUES (4, N'AUDI')
INSERT [dbo].[tbl_CarBrand] ([c_br_ID], [c_br_name]) VALUES (5, N'HONDA')
SET IDENTITY_INSERT [dbo].[tbl_CarBrand] OFF
SET IDENTITY_INSERT [dbo].[tbl_CarColor] ON 

INSERT [dbo].[tbl_CarColor] ([c_col_ID], [c_col_name]) VALUES (1, N'Red')
INSERT [dbo].[tbl_CarColor] ([c_col_ID], [c_col_name]) VALUES (2, N'Black')
INSERT [dbo].[tbl_CarColor] ([c_col_ID], [c_col_name]) VALUES (3, N'Yellow')
INSERT [dbo].[tbl_CarColor] ([c_col_ID], [c_col_name]) VALUES (4, N'Blue')
INSERT [dbo].[tbl_CarColor] ([c_col_ID], [c_col_name]) VALUES (5, N'White')
INSERT [dbo].[tbl_CarColor] ([c_col_ID], [c_col_name]) VALUES (6, N'Green')
SET IDENTITY_INSERT [dbo].[tbl_CarColor] OFF
SET IDENTITY_INSERT [dbo].[tbl_CarStatus] ON 

INSERT [dbo].[tbl_CarStatus] ([c_st_ID], [c_st_name]) VALUES (1, N'Active')
INSERT [dbo].[tbl_CarStatus] ([c_st_ID], [c_st_name]) VALUES (2, N'Delete')
SET IDENTITY_INSERT [dbo].[tbl_CarStatus] OFF
SET IDENTITY_INSERT [dbo].[tbl_CarType] ON 

INSERT [dbo].[tbl_CarType] ([c_ty_ID], [c_ty_name]) VALUES (1, N'4 seater cars')
INSERT [dbo].[tbl_CarType] ([c_ty_ID], [c_ty_name]) VALUES (2, N'7 seater cars')
INSERT [dbo].[tbl_CarType] ([c_ty_ID], [c_ty_name]) VALUES (3, N'9 seater cars')
INSERT [dbo].[tbl_CarType] ([c_ty_ID], [c_ty_name]) VALUES (4, N'12 seater cars')
INSERT [dbo].[tbl_CarType] ([c_ty_ID], [c_ty_name]) VALUES (5, N'16 seater cars')
INSERT [dbo].[tbl_CarType] ([c_ty_ID], [c_ty_name]) VALUES (6, N'45 seater cars')
SET IDENTITY_INSERT [dbo].[tbl_CarType] OFF
SET IDENTITY_INSERT [dbo].[tbl_Discount] ON 

INSERT [dbo].[tbl_Discount] ([dsc_ID], [dsc_code], [dsc_exDate], [dsc_value], [dsc_statusID]) VALUES (0, NULL, CAST(N'2020-01-01 00:00:00.000' AS DateTime), 0, 2)
INSERT [dbo].[tbl_Discount] ([dsc_ID], [dsc_code], [dsc_exDate], [dsc_value], [dsc_statusID]) VALUES (1, N'HUGEDISCOUNTAWAIT', CAST(N'2020-05-13 00:00:00.000' AS DateTime), 50, 1)
INSERT [dbo].[tbl_Discount] ([dsc_ID], [dsc_code], [dsc_exDate], [dsc_value], [dsc_statusID]) VALUES (2, N'DISCOUNTCODEFORUSER', CAST(N'2020-05-13 00:00:00.000' AS DateTime), 20, 2)
INSERT [dbo].[tbl_Discount] ([dsc_ID], [dsc_code], [dsc_exDate], [dsc_value], [dsc_statusID]) VALUES (3, N'GJHADAHJDAKDSDAD', CAST(N'2020-12-14 00:00:00.000' AS DateTime), 30, 1)
INSERT [dbo].[tbl_Discount] ([dsc_ID], [dsc_code], [dsc_exDate], [dsc_value], [dsc_statusID]) VALUES (4, N'NEWFORONEUSER', CAST(N'2020-12-30 00:00:00.000' AS DateTime), 40, 2)
SET IDENTITY_INSERT [dbo].[tbl_Discount] OFF
INSERT [dbo].[tbl_Discount_Status] ([dsc_st_ID], [dsc_st_status]) VALUES (1, N'Avail')
INSERT [dbo].[tbl_Discount_Status] ([dsc_st_ID], [dsc_st_status]) VALUES (2, N'Used')
INSERT [dbo].[tbl_ImageUI] ([ui_name], [ui_image]) VALUES (N'banner', N'banner.png')
INSERT [dbo].[tbl_ImageUI] ([ui_name], [ui_image]) VALUES (N'logo', N'logo.png')
INSERT [dbo].[tbl_ImageUI] ([ui_name], [ui_image]) VALUES (N'seacrhImg', N'seacrhImg.jpg')
INSERT [dbo].[tbl_ImageUI] ([ui_name], [ui_image]) VALUES (N'searchIcon', N'searchIcon.jpg')
SET IDENTITY_INSERT [dbo].[tbl_Rating] ON 

INSERT [dbo].[tbl_Rating] ([rt_ID], [rt_u_email], [rt_CarID], [rt_Value]) VALUES (1, N'truonghanguyen1999@gmail.com', 3, 7)
INSERT [dbo].[tbl_Rating] ([rt_ID], [rt_u_email], [rt_CarID], [rt_Value]) VALUES (2, N'usera@gmail.com', 3, 10)
INSERT [dbo].[tbl_Rating] ([rt_ID], [rt_u_email], [rt_CarID], [rt_Value]) VALUES (3, N'truonghanguyen1999@gmail.com', 1, 5)
INSERT [dbo].[tbl_Rating] ([rt_ID], [rt_u_email], [rt_CarID], [rt_Value]) VALUES (4, N'usera@gmail.com', 1, 9)
SET IDENTITY_INSERT [dbo].[tbl_Rating] OFF
INSERT [dbo].[tbl_UserInfo] ([u_email], [u_name], [u_password], [u_roleID], [u_statusID], [u_phone], [u_address], [u_createDate]) VALUES (N'admin@gmail.com', N'Admin', N'bef57ec7f53a6d40beb640a780a639c83bc29ac8a9816f1fc6c5c6dcd93c4721', N'm01', N's01', N'0908121212', N'abc', CAST(N'2020-01-02 00:00:00.000' AS DateTime))
INSERT [dbo].[tbl_UserInfo] ([u_email], [u_name], [u_password], [u_roleID], [u_statusID], [u_phone], [u_address], [u_createDate]) VALUES (N'truonghanguyen1999@gmail.com', N'Truong Ha', N'12673f95dfdb960f3f097c93c6bb88773366a56e7f06e93d7201238465b87bee', N'c01', N's01', N'0908895555', N'abcdef', CAST(N'2020-03-10 07:45:45.437' AS DateTime))
INSERT [dbo].[tbl_UserInfo] ([u_email], [u_name], [u_password], [u_roleID], [u_statusID], [u_phone], [u_address], [u_createDate]) VALUES (N'usera@gmail.com', N'User A', N'43595993db0980e4b214bd521a3d324d4226168421db12d755eb08079316ddf1', N'c01', N's01', N'0908896666', N'abcdefhgj', CAST(N'2020-03-10 08:08:31.473' AS DateTime))
INSERT [dbo].[tbl_UserInfo] ([u_email], [u_name], [u_password], [u_roleID], [u_statusID], [u_phone], [u_address], [u_createDate]) VALUES (N'userc@gmail.com', N'User C', N'0823f98ca3104a7a2b042d3778e3177f11a07a1910df278097687be3f31c7d22', N'c01', N's02', N'1234567890', N'shjgjadadf', CAST(N'2020-03-11 10:32:10.410' AS DateTime))
INSERT [dbo].[tbl_UserInfo] ([u_email], [u_name], [u_password], [u_roleID], [u_statusID], [u_phone], [u_address], [u_createDate]) VALUES (N'userd@gmail.com', N'User D', N'b5101198dde478aff4c9075fef5a0a13a54abff97a1321309e391c44e47fae09', N'c01', N's02', N'1234567890', N'jkjkhjkhkjjk', CAST(N'2020-03-13 07:55:01.437' AS DateTime))
INSERT [dbo].[tbl_UserRole] ([u_rl_roleID], [u_rl_role]) VALUES (N'c01', N'Customer')
INSERT [dbo].[tbl_UserRole] ([u_rl_roleID], [u_rl_role]) VALUES (N'm01', N'Manager')
INSERT [dbo].[tbl_UserStatus] ([u_st_statusID], [u_st_status]) VALUES (N's01', N'Active')
INSERT [dbo].[tbl_UserStatus] ([u_st_statusID], [u_st_status]) VALUES (N's02', N'New')
ALTER TABLE [dbo].[tbl_Booking_Bill]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_Bill_tbl_Booking_Status] FOREIGN KEY([bill_statusID])
REFERENCES [dbo].[tbl_Booking_Status] ([bk_st_ID])
GO
ALTER TABLE [dbo].[tbl_Booking_Bill] CHECK CONSTRAINT [FK_tbl_Booking_Bill_tbl_Booking_Status]
GO
ALTER TABLE [dbo].[tbl_Booking_Bill]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_Bill_tbl_Discount] FOREIGN KEY([bill_discountID])
REFERENCES [dbo].[tbl_Discount] ([dsc_ID])
GO
ALTER TABLE [dbo].[tbl_Booking_Bill] CHECK CONSTRAINT [FK_tbl_Booking_Bill_tbl_Discount]
GO
ALTER TABLE [dbo].[tbl_Booking_Bill]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_Bill_tbl_UserInfo] FOREIGN KEY([bill_u_email])
REFERENCES [dbo].[tbl_UserInfo] ([u_email])
GO
ALTER TABLE [dbo].[tbl_Booking_Bill] CHECK CONSTRAINT [FK_tbl_Booking_Bill_tbl_UserInfo]
GO
ALTER TABLE [dbo].[tbl_Booking_Bill_Details]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_Bill_Details_tbl_Booking_Bill] FOREIGN KEY([dt_bill_ID])
REFERENCES [dbo].[tbl_Booking_Bill] ([bill_ID])
GO
ALTER TABLE [dbo].[tbl_Booking_Bill_Details] CHECK CONSTRAINT [FK_tbl_Booking_Bill_Details_tbl_Booking_Bill]
GO
ALTER TABLE [dbo].[tbl_Booking_Bill_Details]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_Bill_Details_tbl_Booking_Details_Status] FOREIGN KEY([dt_statusID])
REFERENCES [dbo].[tbl_Booking_Details_Status] ([bk_dt_st_ID])
GO
ALTER TABLE [dbo].[tbl_Booking_Bill_Details] CHECK CONSTRAINT [FK_tbl_Booking_Bill_Details_tbl_Booking_Details_Status]
GO
ALTER TABLE [dbo].[tbl_Booking_Bill_Details]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Booking_Bill_Details_tbl_Car] FOREIGN KEY([dt_carID])
REFERENCES [dbo].[tbl_Car] ([c_ID])
GO
ALTER TABLE [dbo].[tbl_Booking_Bill_Details] CHECK CONSTRAINT [FK_tbl_Booking_Bill_Details_tbl_Car]
GO
ALTER TABLE [dbo].[tbl_Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_CarBrand] FOREIGN KEY([c_brandID])
REFERENCES [dbo].[tbl_CarBrand] ([c_br_ID])
GO
ALTER TABLE [dbo].[tbl_Car] CHECK CONSTRAINT [FK_Car_CarBrand]
GO
ALTER TABLE [dbo].[tbl_Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_CarColor] FOREIGN KEY([c_colorID])
REFERENCES [dbo].[tbl_CarColor] ([c_col_ID])
GO
ALTER TABLE [dbo].[tbl_Car] CHECK CONSTRAINT [FK_Car_CarColor]
GO
ALTER TABLE [dbo].[tbl_Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_CarStatus] FOREIGN KEY([c_statusID])
REFERENCES [dbo].[tbl_CarStatus] ([c_st_ID])
GO
ALTER TABLE [dbo].[tbl_Car] CHECK CONSTRAINT [FK_Car_CarStatus]
GO
ALTER TABLE [dbo].[tbl_Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_CarType] FOREIGN KEY([c_typeID])
REFERENCES [dbo].[tbl_CarType] ([c_ty_ID])
GO
ALTER TABLE [dbo].[tbl_Car] CHECK CONSTRAINT [FK_Car_CarType]
GO
ALTER TABLE [dbo].[tbl_Discount]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Discount_tbl_Discount_Status] FOREIGN KEY([dsc_statusID])
REFERENCES [dbo].[tbl_Discount_Status] ([dsc_st_ID])
GO
ALTER TABLE [dbo].[tbl_Discount] CHECK CONSTRAINT [FK_tbl_Discount_tbl_Discount_Status]
GO
ALTER TABLE [dbo].[tbl_Rating]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Rating_tbl_Car] FOREIGN KEY([rt_CarID])
REFERENCES [dbo].[tbl_Car] ([c_ID])
GO
ALTER TABLE [dbo].[tbl_Rating] CHECK CONSTRAINT [FK_tbl_Rating_tbl_Car]
GO
ALTER TABLE [dbo].[tbl_Rating]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Rating_tbl_UserInfo] FOREIGN KEY([rt_u_email])
REFERENCES [dbo].[tbl_UserInfo] ([u_email])
GO
ALTER TABLE [dbo].[tbl_Rating] CHECK CONSTRAINT [FK_tbl_Rating_tbl_UserInfo]
GO
ALTER TABLE [dbo].[tbl_UserInfo]  WITH CHECK ADD  CONSTRAINT [FK_UserInfo_UserRole] FOREIGN KEY([u_roleID])
REFERENCES [dbo].[tbl_UserRole] ([u_rl_roleID])
GO
ALTER TABLE [dbo].[tbl_UserInfo] CHECK CONSTRAINT [FK_UserInfo_UserRole]
GO
ALTER TABLE [dbo].[tbl_UserInfo]  WITH CHECK ADD  CONSTRAINT [FK_UserInfo_UserStatus] FOREIGN KEY([u_statusID])
REFERENCES [dbo].[tbl_UserStatus] ([u_st_statusID])
GO
ALTER TABLE [dbo].[tbl_UserInfo] CHECK CONSTRAINT [FK_UserInfo_UserStatus]
GO
USE [master]
GO
ALTER DATABASE [CarRental] SET  READ_WRITE 
GO
