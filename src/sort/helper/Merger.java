package sort.helper;

public final class Merger<T extends Comparable<T>> {
	public final void mergeSS(
			final T[] buffer1,
			final T[] buffer2,
			int left,
			final int center,
			int right
			) {
		++right;
		int r = center + 1;
		int i = left;
		while (i < right) {
			if (right <= r) {
				System.arraycopy(buffer1, left, buffer2, i, (right - i));
				break;
			}
			if (center < left) {
				System.arraycopy(buffer1, r, buffer2, i, (right - i));
				break;
			}
			
			if (buffer1[r].compareTo(buffer1[left]) < 0)
				buffer2[i] = buffer1[r++];
			else
				buffer2[i] = buffer1[left++];
			++i;

		}
	}
	
	private final void mergeSSn(
			final T[] buffer1,
			final T[] buffer2,
			int left,
			final int center,
			int right,
			final int border,
			int from
			) {
		final int end = from + center + border - left - right + 2;		
		
		while(from < end) {
			if (center < left) {
				System.arraycopy(buffer1, right, buffer2, from, end - from);
				break;
			}
			
			if (border < right) {
				System.arraycopy(buffer1, left, buffer2, from, end - from);
				break;
			}
			
			if (buffer1[right].compareTo(buffer1[left]) < 0)
				buffer2[from] = buffer1[right++];
			else
				buffer2[from] = buffer1[left++];
			++from;
		}
	}
	
	public final void mergeSSS(
			final T[] buffer1,
			final T[] buffer2,
			int left,
			final int center1,
			final int center2,
			int right
			) {
		++right;
		int r1 = center1 + 1;
		int r2 = center2 + 1;
		int i = left;
		while (i < right) {
			if (center1 < left) {
				mergeSSn(buffer1, buffer2, r1, center2, r2, --right, i);
				break;
			}
			if (center2 < r1) {
				mergeSSn(buffer1, buffer2, left, center1, r2, --right, i);
				break;
			}
			if (right <= r2) {
				mergeSSn(buffer1, buffer2, left, center1, r1, center2, i);
				break;
			}
			
			if (buffer1[r2].compareTo(buffer1[r1]) < 0) {
				if (buffer1[r2].compareTo(buffer1[left]) < 0)
						buffer2[i] = buffer1[r2++];
				else
					buffer2[i] = buffer1[left++];
			} else {
				if (buffer1[r1].compareTo(buffer1[left]) < 0)
					buffer2[i] = buffer1[r1++];
				else
					buffer2[i] = buffer1[left++];
			}
			++i;

		}
	}

	public final void mergeSB(
			final T[] buffer1,
			final T[] buffer2,
			int left,
			final int center,
			int right
			) {
		final int border = right + 1;
		int i = left;
		while (i < border) {
			if (buffer1[right].compareTo(buffer1[left]) < 0)
				buffer2[i] = buffer1[right--];
			else
				buffer2[i] = buffer1[left++];
			i++;

			if (right - 1 < center) {
				while (i < border)
					buffer2[i++] = buffer1[left++];
				break;
			}
			if (center < left) {
				while (i < border)
					buffer2[i++] = buffer1[right--];
				break;
			}
		}
	}

	public final void mergeBS(
			final T[] buffer1,
			final T[] buffer2,
			final int left,
			final int center,
			int right
			) {
		++right;
		int l = center;
		int r = center + 1;
		int i = left;
		while (i < right) {
			if (buffer1[r].compareTo(buffer1[l]) < 0)
				buffer2[i] = buffer1[r++];
			else
				buffer2[i] = buffer1[l--];
			i++;

			if (right <= r) {
				while (i < right)
					buffer2[i++] = buffer1[l--];
				break;
			}
			if (l < left) {
				while (i < right)
					buffer2[i++] = buffer1[r++];
				break;
			}
		}
	}

	public final void mergeBB(
			final T[] buffer1,
			final T[] buffer2,
			final int left,
			final int center,
			int right
			) {
		final int border = right + 1;
		int l = center;
		int i = left;
		while (i < border) {
			if (buffer1[right].compareTo(buffer1[l]) < 0)
				buffer2[i] = buffer1[right--];
			else
				buffer2[i] = buffer1[l--];
			i++;

			if (right - 1 < center) {
				while (i < border)
					buffer2[i++] = buffer1[l--];
				break;
			}
			if (l < left) {
				while (i < border)
					buffer2[i++] = buffer1[right--];
				break;
			}
		}
	}

	public final void dynMerge(
			final T[] buffer1,
			final T[] buffer2,
			final int mode,
			final int left,
			final int center,
			final int right
			) {
		switch (mode) {
		case 1:
			mergeBS(buffer1, buffer2, left, center, right);
			break;
		case 2:
			mergeSB(buffer1, buffer2, left, center, right);
			break;
		case 3:
			mergeBB(buffer1, buffer2, left, center, right);
			break;
		default:
			mergeSS(buffer1, buffer2, left, center, right);
		}
	}

	public final void reverse(
			final T[] buffer1,
			final T[] buffer2,
			int left,
			final int right
			) {
		int i = right;
		while (left < right + 1)
			buffer2[left++] = buffer1[i--];
	}

	public final int modeS(final T[] buffer1, int left) {
		while (++left < buffer1.length) {
			if (buffer1[left].compareTo(buffer1[left - 1]) < 0)
				break;
		}
		return --left;
	}

	public final int modeB(final T[] buffer1, int left) {
		while (++left < buffer1.length) {
			if (buffer1[left - 1].compareTo(buffer1[left]) < 0)
				break;
		}
		return --left;
	}
}