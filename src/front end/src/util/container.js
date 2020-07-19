const container = {
    hasDigit(str) { // 判断字符串是否包含数字符号

        for (var i in str) {

            var asc = str.charCodeAt(i);

            if ((asc >= 48 && asc <= 57)) { // 0: 48

                return true;

            }

        }

        return false;

    },

    hasLetter(str) { // 判断字符串是否包含字母

        for (var i in str) {

            var asc = str.charCodeAt(i);

            if ((asc >= 65 && asc <= 90) || (asc >= 97 && asc <= 122)) {

                return true;

            }

        }

        return false;

    },

    hasUpperLetter(str) { // 判断字符串是否包含大写字母

        for (var i in str) {

            var asc = str.charCodeAt(i);

            if (asc >= 65 && asc <= 90) { // A: 65

                return true;

            }

        }

        return false;

    },

    hasLowerLetter(str) { // 判断字符串是否包含小写字母

        for (var i in str) {

            var asc = str.charCodeAt(i);

            if (asc >= 97 && asc <= 122) { // a: 97

                return true;

            }

        }

        return false;

    },

}

export default container