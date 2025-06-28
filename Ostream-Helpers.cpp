#include <iostream>
#include <vector>

// Ostream to print a matrix
std::ostream& operator<<(std::ostream& os, const std::vector<std::vector<int>>& matrix) {
    os << "[\n";
    for (size_t i = 0; i < matrix.size(); ++i) {
        os << "  [";
        for (size_t j = 0; j < matrix[i].size(); ++j) {
            os << matrix[i][j];
            if (j < matrix[i].size() - 1) {
                os << ", ";
            }
        }
        os << "]";
        if (i < matrix.size() - 1) {
            os << ",";
        }
        os << "\n";
    }
    os << "]";
    return os;
}

// Ostream to print a vector
std::ostream& operator<<(std::ostream& os, const std::vector<int>& vec) {
    os << "[";
    for (size_t i = 0; i < vec.size(); ++i) {
        os << vec[i];
        if (i < vec.size() - 1) {
            os << ", ";
        }
    }
    os << "]";
    return os;
}