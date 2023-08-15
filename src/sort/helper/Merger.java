package sort.helper;

public final class Merger<T extends Comparable<T>> {
	public final void mergeSS(
			final T[] buffer1,
			final T[] buffer2,
			final int left,
			final int center,
			final int right
			) {
		final int border = right + 1;
		int l = left;
		int r = center + 1;
		int i = left;
		while (i < border) {
			if (buffer1[r].compareTo(buffer1[l]) < 0)
				buffer2[i] = buffer1[r++];
			else
				buffer2[i] = buffer1[l++];
			i++;

			if (right < r) {
				while (i < border)
					buffer2[i++] = buffer1[l++];
				break;
			}
			if (center < l) {
				while (i < border)
					buffer2[i++] = buffer1[r++];
				break;
			}
		}
	}

	public final void mergeSB(
			final T[] buffer1,
			final T[] buffer2,
			final int left,
			final int center,
			final int right
			) {
		final int border = right + 1;
		int l = left;
		int r = right;
		int i = left;
		while (i < border) {
			if (buffer1[r].compareTo(buffer1[l]) < 0)
				buffer2[i] = buffer1[r--];
			else
				buffer2[i] = buffer1[l++];
			i++;

			if (r - 1 < center) {
				while (i < border)
					buffer2[i++] = buffer1[l++];
				break;
			}
			if (center < l) {
				while (i < border)
					buffer2[i++] = buffer1[r--];
				break;
			}
		}
	}

	public final void mergeBS(
			final T[] buffer1,
			final T[] buffer2,
			final int left,
			final int center,
			final int right) {
		final int border = right + 1;
		int l = center;
		int r = center + 1;
		int i = left;
		while (i < border) {
			if (buffer1[r].compareTo(buffer1[l]) < 0)
				buffer2[i] = buffer1[r++];
			else
				buffer2[i] = buffer1[l--];
			i++;

			if (right < r) {
				while (i < border)
					buffer2[i++] = buffer1[l--];
				break;
			}
			if (l < left) {
				while (i < border)
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
			final int right) {
		final int border = right + 1;
		int l = center;
		int r = right;
		int i = left;
		while (i < border) {
			if (buffer1[r].compareTo(buffer1[l]) < 0)
				buffer2[i] = buffer1[r--];
			else
				buffer2[i] = buffer1[l--];
			i++;

			if (r - 1 < center) {
				while (i < border)
					buffer2[i++] = buffer1[l--];
				break;
			}
			if (l < left) {
				while (i < border)
					buffer2[i++] = buffer1[r--];
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
			final int right) {
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
			final int left,
			final int right) {
		int i = left;
		int j = right;
		while (i < right + 1)
			buffer2[i++] = buffer1[j--];
	}

	public final int modeS(final T[] buffer1, final int left) {
		int r;
		r = left;
		while (++r < buffer1.length) {
			if (buffer1[r].compareTo(buffer1[r - 1]) < 0)
				break;
		}
		return r - 1;
	}

	public final int modeB(final T[] buffer1, final int left) {
		int r;
		r = left;
		while (++r < buffer1.length) {
			if (buffer1[r - 1].compareTo(buffer1[r]) < 0)
				break;
		}
		return r - 1;
	}
}
