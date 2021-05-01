-- phpMyAdmin SQL Dump
-- version phpStudy 2014
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2018 年 12 月 26 日 07:12
-- 服务器版本: 5.5.53
-- PHP 版本: 5.4.45

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `dh`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(255) DEFAULT NULL,
  `userpass` varchar(255) DEFAULT NULL,
  `regtime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`username`, `userpass`, `regtime`) VALUES
('admin', '123456', '2018-12-02 22:19:44'),
('lyl', '123456', '2018-12-04 22:19:49');

-- --------------------------------------------------------

--
-- 表的结构 `bookloaned`
--

CREATE TABLE IF NOT EXISTS `bookloaned` (
  `readerid` int(11) DEFAULT NULL,
  `readername` varchar(255) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `bookloaned`
--

INSERT INTO `bookloaned` (`readerid`, `readername`, `bookid`, `bookname`, `time`) VALUES
(4, '李四', 12, '高等数学', '2018-07-17'),
(3, '王五', 10, '英语', '2018-12-01'),
(1, '张三', 5, 'javase', '2018-9-18 00:00:00');

-- --------------------------------------------------------

--
-- 表的结构 `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `id` int(20) DEFAULT NULL,
  `bookName` varchar(255) DEFAULT NULL,
  `publisherID` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `typename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `books`
--

INSERT INTO `books` (`id`, `bookName`, `publisherID`, `count`, `price`, `typename`) VALUES
(3, '计算机网络', '电子工业大学出版社', 52, '66', '计算机类'),
(5, 'javase', '西安电子科技大学出版社', 21, '32', '计算机类'),
(6, 'oracle数据库', '西安电子科技大学出版社', 32, '56', '计算机类'),
(7, 'java程序设计', '清华大学出版社', 66, '56', '计算机类'),
(8, 'c++面向对象程序设计', '清华大学出版社', 22, '32', '计算机类'),
(9, '计算机科学导论', '清华大学出版社', 54, '55', '计算机类'),
(10, '英语', '上海交通大学出版社', 21, '56', '英语类'),
(11, '数据挖掘导论', '人民邮电出版社', 11, '56', '计算机类'),
(12, '高等数学', '浙江大学出版社', 19, '10', '数学类'),
(55, '经济法基础', '经济科学出版社', 3, '50', '经济类'),
(56, '操作系统', '西安电子科技大学出版社', 32, '60', '计算机类'),
(33, 'C语言', '清华大学出版社', 20, '60', '计算机类');

-- --------------------------------------------------------

--
-- 表的结构 `booktype`
--

CREATE TABLE IF NOT EXISTS `booktype` (
  `typename` varchar(255) DEFAULT NULL,
  `days` int(255) DEFAULT NULL,
  `fk` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `booktype`
--

INSERT INTO `booktype` (`typename`, `days`, `fk`) VALUES
('小说类', 30, 2),
('计算机类', 60, 3),
('生活类', 60, 5),
('教育类', 50, 1),
('英语类', 30, 2),
('文学类', 30, 3),
('哲学类', 25, 4),
('语言', 2, 5),
('励志类', 52, 5);

-- --------------------------------------------------------

--
-- 表的结构 `reader`
--

CREATE TABLE IF NOT EXISTS `reader` (
  `name` varchar(255) DEFAULT NULL,
  `id` int(25) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `reader`
--

INSERT INTO `reader` (`name`, `id`, `idcard`, `age`, `tel`, `time`) VALUES
('小王', 2, '789456321', 18, '321456987', '2018-11-06 02:46:36'),
('小明', 5, '123456789123456', 20, '11111111111', '2018-11-12 11:05:27'),
('王五', 3, '1546554654165', 25, '4564654666', '2018-11-20 14:14:12'),
('张三', 1, '123456487', 12, '545646546', '2018-11-07 14:14:07'),
('李四', 4, '54654654', 25, '65465464', '2018-11-28 14:14:44'),
('吴三', 121, '370283199188715564', 24, '86390055', '2018-12-17 08:43:31');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
