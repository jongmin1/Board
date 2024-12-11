import axios from 'axios';

// 게시글 인터페이스 정의
interface Board {
  id?: number;
  title: string;
  content: string;
  author: string;
}

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/boards',
  headers: {
    'Content-Type': 'application/json',
  },
});

// API 함수 정의
export const getBoards = async (keyword?: string) => {
  try {
    console.log('검색 요청 파라미터:', keyword);
    const response = await apiClient.get('', {
      params: {
        keyword
      }
    });
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('게시글 조회 에러:', {
        message: error.message,
        status: error.response?.status,
        data: error.response?.data
      });
    }
    throw error;
  }
};

export const getBoardById = async (id: number) => {
  try {
    const response = await apiClient.get(`/${id}`);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('게시글 상세 조회 에러:', {
        message: error.message,
        status: error.response?.status,
        data: error.response?.data
      });
    }
    throw error;
  }
};

export const createBoard = async (data: Board) => {
  try {
    console.log('전송할 데이터:', data);
    const response = await apiClient.post('', data);
    console.log('응답 데이터:', response.data);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('게시글 생성 에러:', {
        message: error.message,
        status: error.response?.status,
        data: error.response?.data
      });
    }
    throw error;
  }
};

export const updateBoard = async (id: number, data: Board) => {
  try {
    const response = await apiClient.put(`/${id}`, data);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('게시글 수정 에러:', {
        message: error.message,
        status: error.response?.status,
        data: error.response?.data
      });
    }
    throw error;
  }
};

export const deleteBoard = async (id: number) => {
  try {
    const response = await apiClient.delete(`/${id}`);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('게시글 삭제 에러:', {
        message: error.message,
        status: error.response?.status,
        data: error.response?.data
      });
    }
    throw error;
  }
};
